package Model;


import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="Suser")
public class SUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom ;
	private String prenom ;
	private String descriptionProfile;
	private String adresse;
	private String phone_number;
	private Date date_birth;
	private String personalWebsite;
	private String Facebbok;
	private String Instagram;
	private String Linkedin;
	
	public SUser(String nom, String prenom, String email, String mdp) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
	}
	private String email ;
	@Column(nullable = false)
	private String mdp;

	
	//hethye el courses elli el user enrolled fihom
	
	 @ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      })
		  @JoinTable(name = "user_enrolled_courses",
		        joinColumns = { @JoinColumn(name = "user_id") },
		        inverseJoinColumns = { @JoinColumn(name = "id_cc") })
	    private Set<Course> courses = new HashSet<>();
	 
	 //hathom el courses mte3 el user haka
	 
	 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	    private List<Course> Owncourses;

	
	public List<Course> getOwncourses() {
		return Owncourses;
	}
	public void setOwncourses(List<Course> owncourses) {
		Owncourses = owncourses;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	
	public SUser(int id, String nom, String prenom,  String email, String mdp) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
	}
	public SUser() {}
	@SuppressWarnings("serial")
	public static  class UserNotFoundException extends RuntimeException {

	    public UserNotFoundException(String message) {
	        super(message);
	    }
	}
	@SuppressWarnings("serial")
	public static  class InvalidPasswordException extends RuntimeException {

	    public InvalidPasswordException(String message) {
	        super(message);
	    }
	    
	}
	@SuppressWarnings("serial")
	public static class EmailAlreadyExistsException extends RuntimeException {

	    public EmailAlreadyExistsException(String message) {
	        super(message);
	    }
	}

}

