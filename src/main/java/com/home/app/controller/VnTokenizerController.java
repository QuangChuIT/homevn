package com.home.app.controller;

import ai.vitk.tok.Tokenizer;
import ai.vitk.type.Token;
import com.home.app.impl.constant.VnTokenizerConstant;
import com.home.app.service.kernel.json.JSONArray;
import com.home.app.service.kernel.json.JSONFactoryUtil;
import com.home.app.service.kernel.json.JSONObject;
import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VnTokenizerController {

    @RequestMapping(value = "/vntokenizer", method = RequestMethod.POST, consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
    public String process(@RequestBody String data) {
        System.out.println("Getting data to process");
        JSONObject json = JSONFactoryUtil.createJSONObject(data);
        String content = json.getString("data");
        try {
            Tokenizer tokenizer = new Tokenizer();
            List<Token> words = tokenizer.tokenize(content);
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
            for (Token token : words) {
                JSONObject jsonObject = null;
                String type = token.getLemma();
                if (type.equals(VnTokenizerConstant.ALLCAP) ||
                        type.equals(VnTokenizerConstant.CAPITAL) ||
                        type.equals(VnTokenizerConstant.HYPHEN) ||
                        type.equals(VnTokenizerConstant.NAME) ||
                        type.equals(VnTokenizerConstant.WORD) ||
                        type.equals(VnTokenizerConstant.NUMBER)
                ) {
                    String word = token.getWord();
                    if (type.equals(VnTokenizerConstant.WORD)) {
                        String[] arr = word.split(" ");
                        if (arr.length >= 2) {
                            jsonObject = JSONFactoryUtil.createJSONObject();
                            jsonObject.put("value", word);
                            jsonObject.put("type", type);
                        }
                    } else {
                        jsonObject = JSONFactoryUtil.createJSONObject();
                        jsonObject.put("value", word);
                        jsonObject.put("type", type);
                    }
                }
                if(jsonObject != null){
                    jsonArray.put(jsonObject);
                }
            }
            return jsonArray.toString();
        } catch (Exception e) {
            LOG.error(e);
        }
        return "";
    }

    private static final Log LOG = LogFactoryUtil.getLog(VnTokenizerController.class);
}
