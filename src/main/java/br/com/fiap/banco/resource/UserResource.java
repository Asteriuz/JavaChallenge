package br.com.fiap.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.HasChildException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.User;
import br.com.fiap.banco.service.UserService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/user")
public class UserResource {

    private UserService service;

    public UserResource() throws ClassNotFoundException, SQLException {
        service = new UserService();
    }

    // GET http://localhost:8080/JavaChallenge/api/user/query?nome=Augusto
    // (Pesquisar por
    // nome)
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> pesquisar(@QueryParam("nome") String pesquisa) throws SQLException {
        return service.pesquisarPorNome(pesquisa);
    }

    // GET http://localhost:8080/JavaChallenge/api/user (Listar todas os users)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> lista() throws ClassNotFoundException, SQLException {
        return service.listar();
    }

    // GET http://localhost:8080/JavaChallenge/api/user/1 (Buscar um user pelo id)
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response busca(@PathParam("id") int codigo) throws ClassNotFoundException, SQLException {
        try {
            return Response.ok(service.pesquisar(codigo)).build();
        } catch (IdNotFoundException e) {
            // Retornar 404 caso a user não exista
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    // POST http://localhost:8080/JavaChallenge/api/user (Cadastrar um user)
    // Exemplo:
    /*
     * {
     * "nome": "Gabriella Zannoto",
     * "email": "gabizinha123@gmail.com"
     * }
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(User user, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
        try {
            service.cadastrar(user);
            // Recupera o path (URL atual(http://localhost:8080/JavaChallenge/api/vaga))
            UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
            // Adiciona o id do user que foi criado na URL
            uriBuilder.path(String.valueOf(user.getCodigo()));
            // Retornar o status 201 com a URL para acessar o user criada
            return Response.created(uriBuilder.build()).build();
        } catch (BadInfoException e) {
            e.printStackTrace();
            // Retornar o status 400 bad request
            return Response.status(Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    // PUT http://localhost:8080/JavaChallenge/api/user/1 (Atualizar um user)
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(User user, @PathParam("id") int codigo)
            throws ClassNotFoundException, SQLException {
        try {
            user.setCodigo(codigo);
            service.atualizar(user);
            return Response.ok().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (BadInfoException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // DELETE http://localhost:8080/JavaChallenge/api/user/1 (Remover um user)
    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            service.remover(id);
            return Response.noContent().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (HasChildException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
