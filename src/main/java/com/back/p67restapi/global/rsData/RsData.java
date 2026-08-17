package com.back.p67restapi.global.rsData;

import com.back.p67restapi.domain.post.comment.dto.PostCommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData {

    private String resultCode;
    private String msg;
    private Object data;
}
