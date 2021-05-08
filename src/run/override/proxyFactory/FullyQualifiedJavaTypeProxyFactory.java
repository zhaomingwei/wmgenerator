package run.override.proxyFactory;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public class FullyQualifiedJavaTypeProxyFactory  extends FullyQualifiedJavaType{

	/**根据路径不同需修改 TODO**/
//	private static FullyQualifiedJavaType pageInfoInstance = new FullyQualifiedJavaType("cn.creditease.core.base.model.PageInfo");
	private static FullyQualifiedJavaType pageInfoInstance = new FullyQualifiedJavaType("base.model.PageInfo");
//	private static FullyQualifiedJavaType baseExampleInstance = new FullyQualifiedJavaType("cn.creditease.core.base.model.BaseExample");
	private static FullyQualifiedJavaType baseExampleInstance = new FullyQualifiedJavaType("base.model.BaseExample");
//	private static FullyQualifiedJavaType baseMapperInstance = new FullyQualifiedJavaType("cn.creditease.core.base.dao.BaseMapper");
	private static FullyQualifiedJavaType baseMapperInstance = new FullyQualifiedJavaType("base.dao.BaseMapper");
//	private static FullyQualifiedJavaType baseServiceInstance = new FullyQualifiedJavaType("cn.creditease.core.base.service.BaseService");
	private static FullyQualifiedJavaType baseServiceInstance = new FullyQualifiedJavaType("base.service.BaseService");
//	private static FullyQualifiedJavaType baseServiceImplInstance = new FullyQualifiedJavaType("cn.creditease.core.base.service.impl.BaseServiceImpl");
	private static FullyQualifiedJavaType baseServiceImplInstance = new FullyQualifiedJavaType("base.service.impl.BaseServiceImpl");

	public FullyQualifiedJavaTypeProxyFactory(String fullTypeSpecification) {
		super(fullTypeSpecification);
	}
	
	public static final FullyQualifiedJavaType getPageInfoInstanceInstance() {

		return pageInfoInstance;
	}
	public static final FullyQualifiedJavaType getBaseExampleInstance() {
		
		return baseExampleInstance;
	}
	
	public static final FullyQualifiedJavaType getBaseMapperInstance() {
		
		return baseMapperInstance;
	}
	public static final FullyQualifiedJavaType getBaseServiceInstance() {
		
		return baseServiceInstance;
	}
	public static final FullyQualifiedJavaType getBaseServiceImplInstance() {
		
		return baseServiceImplInstance;
	}
}
