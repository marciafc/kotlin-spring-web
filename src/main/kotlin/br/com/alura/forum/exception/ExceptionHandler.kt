package br.com.alura.forum.exception

import br.com.alura.forum.dto.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class) // Qual exception ocorreu, no caso NotFoundException
    @ResponseStatus(HttpStatus.NOT_FOUND) // Código http que deverá devolver
    fun handleNotFound(
            exception: NotFoundException,
            request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
                status = HttpStatus.NOT_FOUND.value(),
                error = HttpStatus.NOT_FOUND.name,
                message = exception.message,
                path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class) // erro de validação do bean validation
    @ResponseStatus(HttpStatus.BAD_REQUEST) // devolve 400
    fun handleValidationError(
            exception: MethodArgumentNotValidException,
            request: HttpServletRequest

    ): ErrorView {

        // key: nome do campo -> e.field
        // value: mensagem de erro -> e.defaultMessage
        // exception.bindingResult.fieldErrors lista com todos erros
        val errorMessage = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach{
            e -> errorMessage.put(e.field, e.defaultMessage)
        }
        return ErrorView(
                status = HttpStatus.BAD_REQUEST.value(),
                error = HttpStatus.BAD_REQUEST.name,
                message = errorMessage.toString(),
                path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class) // Exception genérica
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Devolver status code 500
    fun handleServerError(
            exception: Exception,
            request: HttpServletRequest

    ): ErrorView {

        return ErrorView(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                error = HttpStatus.INTERNAL_SERVER_ERROR.name,
                message = exception.message,
                path = request.servletPath
        )
    }

}