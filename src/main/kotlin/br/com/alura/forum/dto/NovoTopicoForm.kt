package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class NovoTopicoForm(

        @field:NotEmpty(message = "Titulo nao pode ser em branco")
        @field:Size(min = 5, max = 100, message = "Titulo deve ter entre 5 e 100 caracteres")
        val titulo: String,

        @field:NotEmpty(message = "Mensagem nao pode ser em branco")
        val mensagem: String,

        @field:NotNull
        @field:Positive(message = "idCurso deve ser maior que zero")
        val idCurso: Long,

        @field:NotNull
        @field:Positive(message = "idAutor deve ser maior que zero")
        val idAutor: Long
)
