package md.ilie.coursesmanager.gateway.graphql;

import org.springframework.stereotype.Component;

@Component
public class CustomGraphQLContextBuilder {

  //  private final EducationService educationService; implements GraphQLServletContextBuilder
  //
  //  public CustomGraphQLContextBuilder(EducationService educationService) {
  //    this.educationService = educationService;
  //  }
  //
  //  @Override
  //  public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
  //    return DefaultGraphQLServletContext.createServletContext(buildDataLoaderRegistry(), null).with(httpServletRequest)
  //        .with(httpServletResponse).build();
  //  }
  //
  //  @Override
  //  public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
  //    return DefaultGraphQLWebSocketContext.createWebSocketContext(buildDataLoaderRegistry(), null).with(session)
  //        .with(handshakeRequest).build();
  //  }
  //
  //  @Override
  //  public GraphQLContext build() {
  //    return new DefaultGraphQLContext(buildDataLoaderRegistry(), null);
  //  }
  //
  //  private DataLoaderRegistry buildDataLoaderRegistry() {
  //    DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();
  //
  //    //    dataLoaderRegistry.register("customerDataLoader",
  //    //        new DataLoader<Long, Customer>(accountIds ->
  //    //            CompletableFuture.supplyAsync(() ->
  //    //                customerRepository.findCustomersByAccountIds(accountIds), new SyncTaskExecutor())));
  //
  //    return dataLoaderRegistry;
  //  }
}