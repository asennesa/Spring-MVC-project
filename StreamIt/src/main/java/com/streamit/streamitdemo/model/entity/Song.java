package com.streamit.streamitdemo.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;
@Entity
@Table(name = "songs")
public class Song extends BaseEntity {
    private String name;
    private String genre;
    private byte[] songFile;
    private LocalDateTime releaseDate;

    public Song() {
    }
    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    @Lob
    @Column(name = "song_file")
    public byte[] getSong() {
        return songFile;
    }

    public void setSong(byte[] songFile) {
        this.songFile = songFile;
    }
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past!")
    @Column(name ="release_date")
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
}
