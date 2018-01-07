<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="http://localhost:8086/res/img/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://localhost:8086/res/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('tbody tr:odd').addClass("trLight");
		
		$(".select-all").click(function(){
			if($(this).attr("checked")){
				$(".checkBox input[type=checkbox]").each(function(){
					$(this).attr("checked", true);  
					});
				}else{
					$(".checkBox input[type=checkbox]").each(function(){
					$(this).attr("checked", false);  
					});
				}
			});
		
		})
</script>
<style type="text/css">
body {
	background:#FFF
}
</style>
</head>

<body>
<div id="contentWrap">
	<!--表格控件 -->
  <div id="widget table-widget">
    <div class="pageTitle">我的待办流程</div>
    <div class="pageColumn">
      <div class="pageButton"></div>
      <table border="3">
        <thead>
        <th width="25"><input class="select-all" name="" type="checkbox" value="" /></th>
          <th width="">流程ID</th>
          <th width="">流程名称</th>
          <th width="">创建时间</th>
          <th width="">当前执行人</th>
          <th width="10%">操作</th>
            </thead>
        <tbody>
         <c:if test="${not empty taskList}">
            	<c:forEach items="${taskList}" var="task">
          		<tr>
            		<td class="checkBox"><input name="ids[]" type="checkbox" value="" /></td>
            		<td>${task.id}</td>
            		<td>${task.name}</td>
            		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"  value="${task.createTime}"/></td>
            		<td>${task.assignee}</td>
            		<td><a href="/activiti/completTaskById?taskId=${task.id}">执行</a>
            		<a href="/activiti/completTaskById?taskId=${task.id}">驳回</a></td>
          		</tr>
          </c:forEach>
            </c:if>
          <!-- <tr class=" ">
            <td colspan="7" class="checkBox">共找到 1000 条记录，每页50条记录 &lt;上页　1　　2 　3　　4　　5　　6　　7　　8　　9　　10　下页&gt;　</td>
          </tr> -->
          
        </tbody>
      </table>
    </div>
  </div><!-- #widget -->
</div>


</body>
</html>
</body>
</html>