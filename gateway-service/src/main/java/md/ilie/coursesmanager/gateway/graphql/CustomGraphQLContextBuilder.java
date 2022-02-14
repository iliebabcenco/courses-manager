package md.ilie.coursesmanager.gateway.graphql;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import md.ilie.coursesmanager.gateway.service.EducationService;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

  private final EducationService educationService;

  public CustomGraphQLContextBuilder(EducationService educationService) {
    this.educationService = educationService;
  }

  @Override
  public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    return DefaultGraphQLServletContext.createServletContext(buildDataLoaderRegistry(), null).with(httpServletRequest)
        .with(httpServletResponse).build();
  }

  @Override
  public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
    return DefaultGraphQLWebSocketContext.createWebSocketContext(buildDataLoaderRegistry(), null).with(session)
        .with(handshakeRequest).build();
  }

  @Override
  public GraphQLContext build() {
    return new DefaultGraphQLContext(buildDataLoaderRegistry(), null);
  }

  private DataLoaderRegistry buildDataLoaderRegistry() {
    DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();

    //    dataLoaderRegistry.register("customerDataLoader",
    //        new DataLoader<Long, Customer>(accountIds ->
    //            CompletableFuture.supplyAsync(() ->
    //                customerRepository.findCustomersByAccountIds(accountIds), new SyncTaskExecutor())));

    return dataLoaderRegistry;
  }
}