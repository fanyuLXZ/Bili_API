package com.dreamwolf.entity.message.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgsDatamap {

    private Integer talker_id;//对话的id
    private Integer ack_seqno;//对话的长度
    private Msgslastmsg msgslastmsg;

}
