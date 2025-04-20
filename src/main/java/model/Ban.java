package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Ban implements Serializable {
    @Id
    @Column(columnDefinition = "nvarchar(5)")
    private String maBan;
    @Column(columnDefinition = "nvarchar(10)", nullable = false)
    private String tenBan;
    @Column(columnDefinition = "nvarchar(10)", nullable = false)
    private String viTri;
    @Column(columnDefinition = "nvarchar(10)", nullable = false)
    private int trangThai;

    @ManyToOne
    @JoinColumn(name = "maLoaiBan")
    private LoaiBan loaiBan;

}
