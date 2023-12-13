package br.com.mydiagram.model

import br.com.mydiagram.enums.MyDiagramUserRoles
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Schema(description = "Model for a user of our website.")
@Document("MyDiagramUsers")
class MyDiagramUser(
    @Id
    @field:Schema(
        description = "The e-mail of a user of our website. It is used as the ID for the queries made to our database.",
        example = "wendel@email.com",
        type = "string",
    )
    val email: String,
    @field:Schema(
        description = "The full name of a user of our website.",
        example = "Fulano dos Santos Nascimento",
        type = "string"
    )
    val name: String?,
    @field:Schema(
        description = "The password of a user of our website. It must follow a series specifications to be considered valid. They are: \n" +
                "-> It must have at least 8 digits; \n " +
                "-> It must have at least a number; \n " +
                "-> It must have at least an uppercase letter; \n " +
                "-> It must have at least a lowercase letter; \n" +
                "-> It must have at least a special character, such as !, @, #, etc. \n",
        example = "12345As#",
        type = "string"
    )
    val pass: String,
    @field:Schema(
        description = "The roles that a user can have while using our website. There are three existing roles: \n " +
                "-> OWNER: Default user role. It enables the user to create, save, change and delete diagrams, and create or destroy a session with other users. \n " +
                "-> EDITOR: \n " +
                "-> SPECTATOR: \n ",
        defaultValue = "OWNER",
        allowableValues = ["OWNER, EDITOR, SPECTATOR"],
        required = false
    )
    val myDiagramUserRoles: MyDiagramUserRoles?
    ) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String {
        return pass
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}