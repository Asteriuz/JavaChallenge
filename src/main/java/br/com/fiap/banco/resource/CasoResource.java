package br.com.fiap.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.Caso;
import br.com.fiap.banco.service.CasoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/caso")
public class CasoResource {

    private CasoService service;

    public CasoResource() throws ClassNotFoundException, SQLException {
        service = new CasoService();
    }

    // GET http://localhost:8080/JavaChallenge/api/casos (Listar todas os casos)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Caso> lista() throws ClassNotFoundException, SQLException {
        return service.listar();
    }

    // GET http://localhost:8080/JavaChallenge/api/casos/<id> (Buscar caso pelo id)
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response busca(@PathParam("id") int codigo) throws ClassNotFoundException, SQLException {
        try {
            return Response.ok(service.pesquisar(codigo)).build();
        } catch (IdNotFoundException e) {
            // Retornar 404 caso o caso não exista
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    // GET http://localhost:8080/JavaChallenge/api/casos/user/<id> (Buscar caso
    // pelo id do user)
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Caso> pesquisarPorUser(@PathParam("id") int codigo)
            throws SQLException, ClassNotFoundException, IdNotFoundException {
        return service.pesquisarPorUser(codigo);
    }

    // GET http://localhost:8080/JavaChallenge/api/casos/user/nome/<nome> (Buscar
    // caso pelo nome do user)
    @GET
    @Path("/user/nome/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Caso> pesquisarPorNomeUser(@PathParam("nome") String nome)
            throws SQLException, ClassNotFoundException, IdNotFoundException {
        return service.pesquisarPorNomeUser(nome);
    }

    // GET http://localhost:8080/JavaChallenge/api/casos/status/<status> (Buscar
    // caso pelo status)
    @GET
    @Path("/status/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Caso> pesquisarPorStatus(@PathParam("status") String status) throws SQLException {
        return service.pesquisarPorStatus(status);
    }

    // POST http://localhost:8080/JavaChallenge/api/casos (Cadastrar um caso)
    // Exemplo:
    /*
     * {
     * "marca": "Corolla",
     * "modelo": "GLi 2.0 Flex",
     * "endereco": "Rua Jardim",
     * "imagem":
     * "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlAyQF-K11ZCDJAvm_FRVstt-hlQokVF3ArazlXmjFtw&s",
     * "status": "PENDENTE",
     * "dataCriacao": "2020-04-01",
     * "user": {
     * "codigo": 1
     * }
     * }
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Caso caso, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
        try {
            System.out.println(caso);
            service.cadastrar(caso);
            // Recupera o path (URL atual(http://localhost:8080/JavaChallenge/api/casos))
            UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
            // Adiciona o id do novo caso criado na URL
            uriBuilder.path(String.valueOf(caso.getCodigo()));
            // Retornar o status 201 com a URL para acessar o novo caso criado
            return Response.created(uriBuilder.build()).build();
        } catch (BadInfoException e) {
            e.printStackTrace();
            // Retornar o status 400 bad request
            return Response.status(Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    // PUT http://localhost:8080/JavaChallenge/api/casos/<id> (Atualizar um caso)
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Caso caso, @PathParam("id") int codigo)
            throws ClassNotFoundException, SQLException {
        try {
            caso.setCodigo(codigo);
            service.atualizar(caso);
            return Response.ok().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (BadInfoException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // DELETE http://localhost:8080/JavaChallenge/api/casos/<id> (Remover um caso)
    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            service.remover(id);
            return Response.noContent().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

}
