package com.example.realestate.domain

import java.util.*
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class IdEntity(@Id val id: UUID = UUID.randomUUID()) {

}