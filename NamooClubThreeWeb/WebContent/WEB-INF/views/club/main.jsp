<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>나무커뮤니티</title>
    <%@ include file="/WEB-INF/views/layout/common.jsp" %>
</head>
<body>

<!-- Main Navigation ========================================================================================== -->
<%@ include file="/WEB-INF/views/layout/menu.jsp" %>

<!-- Header ========================================================================================== -->
<header>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="jumbotron">
                    <h1>${community.name}</h1>
                    <p>${community.description}</p>
                    <p><a href="${ctx}/club/open/form.do?communityId=${community.id}" class="btn btn-warning btn-lg">클럽 개설하기</a></p>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12 col-lg-12">
                <ol class="breadcrumb">
                    <li><a href="${ctx}/community/main.do">Home</a></li>
                    <li class="active">${community.name}</li>
                </ol>
            </div>
        </div>
    </div>
</header>

<!-- Container ======================================================================================= -->
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <!-- ★★★ Contents -->
            <div class="page-header">
                <h2 id="container">클럽</h2>
            </div>

            <ul class="list-group">
                <c:forEach var="club" items="${clubs}">
                    <li class="list-group-item">
                        <span class="badge" title="개설일자"><fmt:formatDate value="${club.openDate}" pattern="yyyy-MM-dd"/></span>
                        <h4><span class="label label-primary" title="클럽 카테고리">${club.categoryName}</span>
                          <c:if test="${club.owned}"><span class="label label-info">관리자</span></c:if>
                          <a href="#">${club.name} (회원:${club.countOfMembers})</a></h4>
                        <p>${club.description}</p>
                        <c:choose>
                          <c:when test="${club.joined}">
                            <a href="${ctx}/club/withdrawal/confirm.do?communityId=${community.id}&clubId=${club.id}" class="btn btn-default btn-sm">멤버탈퇴 신청하기</a>
                          </c:when>
                          <c:otherwise>
                            <a href="${ctx}/club/join/confirm.do?communityId=${community.id}&clubId=${club.id}" class="btn btn-warning btn-sm">멤버 가입하기</a>
                          </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
                <c:if test="${empty clubs}">
                    <li class="list-group-item">
                        <p class="text-center">개설된 클럽이 없습니다.</p>
                    </li>
                </c:if>                
            </ul>
        </div>
    </div>

<!-- Footer ========================================================================================== -->
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

</div>

</body>
</html>