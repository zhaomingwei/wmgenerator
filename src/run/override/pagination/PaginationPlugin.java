package run.override.pagination;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import run.override.mapper.SqlMapIsMergeablePlugin;
import run.override.proxyFactory.FullyQualifiedJavaTypeProxyFactory;

public class PaginationPlugin extends SqlMapIsMergeablePlugin {
	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		// add field, getter, setter for limit clause
//		addLimit(topLevelClass, introspectedTable, "pageInfo");
//		addLimitString(topLevelClass, introspectedTable, "groupByClause");
		
//		topLevelClass.addImportedType(FullyQualifiedJavaTypeProxy.getPageInfoInstanceInstance());

		FullyQualifiedJavaType baseExampleType = FullyQualifiedJavaTypeProxyFactory.getBaseExampleInstance();
		topLevelClass.setSuperClass(baseExampleType);
		
		topLevelClass.addImportedType(baseExampleType);
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}
	
	@Override
	public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		XmlElement isNotNullElement1 = new XmlElement("if"); 
		isNotNullElement1.addAttribute(new Attribute("test", "groupByClause != null")); 
		isNotNullElement1.addElement(new TextElement("group by ${groupByClause}"));
		element.addElement(5, isNotNullElement1);
		XmlElement isNotNullElement = new XmlElement("if");
		isNotNullElement.addAttribute(new Attribute("test", "pageInfo != null")); 
		isNotNullElement.addElement(new TextElement("limit #{pageInfo.pageBegin} , #{pageInfo.pageSize}"));
		element.addElement(isNotNullElement);

		return super.sqlMapUpdateByExampleWithBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		// XmlElement isParameterPresenteElemen = (XmlElement) element
		// .getElements().get(element.getElements().size() - 1);
		XmlElement isNotNullElement1 = new XmlElement("if");
		isNotNullElement1.addAttribute(new Attribute("test", "groupByClause != null"));
		// isNotNullElement.addAttribute(new Attribute("compareValue", "0"));
		isNotNullElement1.addElement(new TextElement("group by ${groupByClause}"));
		// isParameterPresenteElemen.addElement(isNotNullElement);
		element.addElement(5, isNotNullElement1);

		// XmlElement isParameterPresenteElemen = (XmlElement) element
		// .getElements().get(element.getElements().size() - 1);
		XmlElement isNotNullElement = new XmlElement("if"); 
		isNotNullElement.addAttribute(new Attribute("test", "pageInfo != null"));
		// isNotNullElement.addAttribute(new Attribute("compareValue", "0"));
		isNotNullElement.addElement(new TextElement("limit #{pageInfo.pageBegin} , #{pageInfo.pageSize}"));
		// isParameterPresenteElemen.addElement(isNotNullElement);
		element.addElement(isNotNullElement);

		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		XmlElement answer = new XmlElement("select");

		String fqjt = introspectedTable.getExampleType();

		answer.addAttribute(new Attribute("id", introspectedTable.getCountByExampleStatementId()));
		answer.addAttribute(new Attribute("parameterType", fqjt));
		answer.addAttribute(new Attribute("resultType", "java.lang.Integer"));

		this.context.getCommentGenerator().addComment(answer);

		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from ");
		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

		XmlElement ifElement = new XmlElement("if");
		ifElement.addAttribute(new Attribute("test", "_parameter != null"));
		XmlElement includeElement = new XmlElement("include");
		includeElement.addAttribute(new Attribute("refid", introspectedTable.getExampleWhereClauseId()));
		ifElement.addElement(includeElement);

		element.getElements().clear();
		element.getElements().add(new TextElement(sb.toString()));
		element.getElements().add(ifElement);
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	@SuppressWarnings("unused")
	private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		FullyQualifiedJavaType pageInfoInstance = FullyQualifiedJavaTypeProxyFactory.getPageInfoInstanceInstance();
//		Field field = new Field();
//		field.setVisibility(JavaVisibility.PROTECTED);
		// field.setType(FullyQualifiedJavaType.getIntInstance());
		// PrimitiveTypeWrapper integerInstance =
		// PrimitiveTypeWrapper.getIntegerInstance();
//		field.setType(pageInfoInstance);
//		field.setName(name);
//		// field.setInitializationString("-1");
//		commentGenerator.addFieldComment(field, introspectedTable);
//		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(pageInfoInstance, name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(pageInfoInstance);
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
	}

	@SuppressWarnings("unused")
	private void addLimitString(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		FullyQualifiedJavaType stringInstance = FullyQualifiedJavaType.getStringInstance();
//		Field field = new Field();
//		field.setVisibility(JavaVisibility.PROTECTED);
		// field.setType(FullyQualifiedJavaType.getIntInstance());
		// PrimitiveTypeWrapper integerInstance =
		// PrimitiveTypeWrapper.getIntegerInstance();
//		field.setType(stringInstance);
//		field.setName(name);
//		// field.setInitializationString("-1");
//		commentGenerator.addFieldComment(field, introspectedTable);
//		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(stringInstance, name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(stringInstance);
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		introspectedTable.getFullyQualifiedTable();
	}


}