# Fórum API

## Build automático - sempre que salvar

  - Adicionar dependência devtools
    
  - File \ Settings \ Build, Execution, Deployment \ Compiler \ marcar "Build project automatically"
    
  - Control + Shift + A \ digitar "Registry..." \ marcar "compiler.automake.allow.when.app.running"

## Swagger

  - [Swagger da app](http://localhost:8080/swagger-ui.html)

## Package DTO

  - XXXView: usado para visualização (saída de dados)

  - XXXForm: usado para entrada de dados 

## Validação

  - @Valid
  - Usar @field: quando for data class
    - Usar o decompile do Kotlin para visualizar as validações nos atributos

## Response

- Métodos void no response, o padrão do Spring:

  - Se foi processado com sucesso, retorna 200
  - Se deu alguma exception, retorna 500
  - Se deu erro de validação, erro no client, retorna 400
  
- Para alterar retorno, alterar para ResponseEntity<XXXView>

- Montar URI: UriComponentsBuilder para retornar junto ao 201

  - Cria header 'location' no 'Response headers'

```kotlin
@PostMapping
fun cadastrar(@RequestBody @Valid form: NovoTopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView> {
    
    val topicoView = service.cadastrar(form)
    
    val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
    return ResponseEntity.created(uri).body(topicoView)
}
```

## Modelo de maturidade de Richardson

Define níveis de maturidade na aderência ao modelo REST

  - [Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html)

## HTTP Status codes

  - [Códigos de status de respostas HTTP](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status)

## Tratamento de erros

Ao invés de devolver toda stack, devolve resposta personalizada

  - Classe ExceptionHandler - @RestControllerAdvice
    
    - @ExceptionHandler(NotFoundException::class): quando não localizou o recurso
      - Classe NotFoundException - RuntimeException
      - Classe Service: orElseThrow{NotFoundException(notFoundMessage)
      
    - @ExceptionHandler(MethodArgumentNotValidException::class): quando ocorre erro de validação (bean validation)
      - Exemplo @field:NotEmpty
        
    - @ExceptionHandler(Exception::class): erro genérico
      
    - Erros não tratados nesta classe, seguem com tratamento default
    
  - ErrorView: dto que representa o erro ocorrido pela API

## Referência

  - [Construção de API REST com Swagger e Kotlin](https://www.redspark.io/construcao-de-api-rest-com-swagger-e-kotlin/)

  - [Construção de API REST com Swagger e Kotlin - Github](https://github.com/vqmonteiro/autorizacao)
    
  - [Projeto](https://github.com/alura-cursos/2208-kotlin-spring)

  - [Bean Validation no Kotlin](https://blog.caelum.com.br/bean-validation-no-kotlin/amp/)
    - Validação no Kotlin
    - Decompile para Java
    
  - [MapStruct with Kotlin](https://github.com/mapstruct/mapstruct-examples/tree/master/mapstruct-kotlin)    