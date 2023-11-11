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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/casos")
public class CasoResource {

    private CasoService service;

    public CasoResource() throws ClassNotFoundException, SQLException {
        service = new CasoService();
    }

    // GET http://localhost:8080/JavaChallenge/api/query?nome=<nome> (Pesquisar por
    // nome)
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Caso> pesquisar(@QueryParam("nome") String pesquisa) throws SQLException {
        return service.pesquisarPorNome(pesquisa);
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
            // Retornar 404 caso a empresa não exista
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/status/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Caso> pesquisarPorStatus(@PathParam("status") String status) throws SQLException {
        return service.pesquisarPorStatus(status);
    }

    // POST http://localhost:8080/JavaChallenge/api/casos (Cadastrar um caso)
    // example:
    // {
    // "cd_empresa": 1,
    // "nm_empresa": "ABC Company",
    // "nr_funcionario": 100
    // }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Caso empresa, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
        try {
            service.cadastrar(empresa);
            // Recupera o path (URL atual(http://localhost:8080/JavaChallenge/api/casos))
            UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
            // Adiciona o id do novo caso criado na URL
            uriBuilder.path(String.valueOf(empresa.getCodigo()));
            // Retornar o status 201 com a URL para acessar o novo caso criado
            return Response.created(uriBuilder.build()).build();
        } catch (BadInfoException e) {
            e.printStackTrace();
            // Retornar o status 400 bad request
            return Response.status(Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    // PUT http://localhost:8080/JavaChallenge/api/casos/<id> (Atualizar uma
    // empresa)
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Caso empresa, @PathParam("id") int codigo)
            throws ClassNotFoundException, SQLException {
        try {
            empresa.setCodigo(codigo);
            service.atualizar(empresa);
            return Response.ok().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (BadInfoException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // DELETE http://localhost:8080/Cp6/api/empresa/<id> (Remover uma empresa)
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
