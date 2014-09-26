package com.nms.mediastore.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_MUSICLINK")
@NamedQueries({
    @NamedQuery(
            name = "MusicLink.findByUUID",
            query = "SELECT ml FROM MusicLink ml WHERE ml.uuid = :uuid AND ml.expireDate >= CURRENT_TIMESTAMP"
    )
})
@XmlRootElement
public class MusicLink extends BaseEntity {

    private static final long serialVersionUID = -7153926835169422111L;

    @PrePersist
    public void generateUUID() {
        uuid = UUID.randomUUID().toString();
    }

    @NotNull
    @Column(name = "UUID", nullable = false, length = 100, unique = true)
    protected String uuid;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "EXPIREDATE", nullable = false)
    protected Date expireDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MUSICID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected Music music;

    public MusicLink() {
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
