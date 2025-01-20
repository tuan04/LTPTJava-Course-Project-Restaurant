package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class LoaiBan {
    @Id
    @Column(columnDefinition = "nvarchar(5)")
    private String maLoaiBan;
    @Column(columnDefinition = "nvarchar(15)", nullable = false)
    private String tenLoaiBan;
}
