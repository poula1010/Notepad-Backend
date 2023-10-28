package com.pooh.notebook.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false ,unique = true)
    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Note> notes;

    public void addNote(Note note){
        if(notes == null){
            notes=new ArrayList<>();
        }
        notes.add(note);
        note.setUser(this);
    }
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
            @JoinTable(
                    name="users_roles",
                    joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
            )
    private Set<Role> roles;
}
