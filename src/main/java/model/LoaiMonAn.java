package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class LoaiMonAn implements Serializable {
    @Id
    @Column(columnDefinition = "nvarchar(5)")
    private String maLoaiMon;

    @Column(columnDefinition = "nvarchar(40)", nullable = false)
    private String tenLoaiMon;
}
