package com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity

import org.hibernate.Hibernate
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.entity.Person as PersonImdb
import java.util.Date
import javax.persistence.*

@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "person", indexes = [Index(columnList = "apiId", unique = true)])
data class Person (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String,
    var role: String? = null,
    var birthDate: Date? = null,
    var deathDate: Date? = null,
    @Column(columnDefinition = "TEXT")
    var description: String? = null,
    var apiId: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Person

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1422108840

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , role = $role , birthDate = $birthDate , deathDate = $deathDate , description = $description , apiId = $apiId )"
    }
}

fun PersonImdb.transform() = Person(
    id = null,
    name = this.name!!,
    role = this.role,
    birthDate = this.birthDate,
    deathDate = this.deathDate,
    description = this.summary,
    apiId = this.id
)