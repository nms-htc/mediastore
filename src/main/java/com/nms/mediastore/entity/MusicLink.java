package com.nms.mediastore.entity;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_MUSICLINK")
@XmlRootElement
public class MusicLink extends BaseEntity {

    private static final long serialVersionUID = -7153926835169422111L;

    @ManyToOne
    @JoinColumn(name = "MUSICID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Music music;

    public MusicLink() {
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
