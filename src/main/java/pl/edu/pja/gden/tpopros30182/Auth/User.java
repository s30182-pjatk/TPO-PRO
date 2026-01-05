package pl.edu.pja.gden.tpopros30182.Auth;

import jakarta.persistence.*;
import pl.edu.pja.gden.tpopros30182.Entities.PersonalCollectionEntry;
import pl.edu.pja.gden.tpopros30182.Entities.PublicCatalogSubmission;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "App_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
    @Column(length = 2048)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "ownerUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PersonalCollectionEntry> collection = new ArrayList<>();

    @OneToMany(mappedBy = "submitter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PublicCatalogSubmission> submissions = new ArrayList<>();

    public User() {
    }
    public User(Long id, String firstName, String lastName, String email, String password) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public List<PersonalCollectionEntry> getCollection() {
        return collection;
    }

    public void setCollection(List<PersonalCollectionEntry> collection) {
        this.collection = collection;
    }

    public List<PublicCatalogSubmission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<PublicCatalogSubmission> submissions) {
        this.submissions = submissions;
    }
}
