package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString

public class Ban {
    @Id
    @Column(columnDefinition = "nvarchar(5)")
    private String maBan;
    @Column(columnDefinition = "nvarchar(10)", nullable = false)
    private String tenBan;
    @Column(columnDefinition = "nvarchar(10)", nullable = false)
    private String viTri;
    @Column(columnDefinition = "nvarchar(10)", nullable = false)
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "maLoaiBan")
    private LoaiBan loaiBan;
}
