package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatLieu {
    private int idChatLieu;
    private String maChatLieu;
    private String tenChatLieu;
    private String moTa;
    private int trangThai;
}
