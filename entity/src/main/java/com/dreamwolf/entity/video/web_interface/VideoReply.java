package com.dreamwolf.entity.video.web_interface;

import com.dreamwolf.entity.comment.web_interface.CommListMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoReply {
    ReplyCursor cursor;
    private List<CommListMap> replies;
}
