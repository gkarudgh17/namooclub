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
                    <h1>나무 커뮤니티와 함께!</h1>
                    <p>나무 커뮤니티와 함께 특정 취미와 관심사, 특정 그룹 또는 조직에 관한 대화를 시작하세요.</p>
                    <p><a href="${ctx}/community/open.xhtml" class="btn btn-warning btn-lg">커뮤니티 개설하기</a></p>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Container ======================================================================================= -->
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <!-- ★★★ Tab Menu -->
            <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                <li class="active"><a href="#joined" data-toggle="tab">가입 커뮤니티</a></li>
                <li class=""><a href="#unjoinded" data-toggle="tab">미가입 커뮤니티</a></li>
            </ul>

            <!-- ★★★ Tab Panel -->
            <div id="communityList" class="tab-content">
                <!-- ★★★ 가입 커뮤니티 -->
                <div class="tab-pane fade active in" id="joined">
                    <div class="page-header">
                        <h2 id="container">가입 커뮤니티</h2>
                    </div>

                    <ul class="list-group">
                        <c:forEach var="community" items="${belongList}">
                            <li class="list-group-item">
                                <span class="badge"><fmt:formatDate value="${community.openDate}" pattern="yyyy-MM-dd"/></span>
                                <h4><a href="${ctx}/club/main.do?communityId=${community.id}">${community.name}</a></h4>
                                <p>${community.description}</p>
                                <a href="${ctx}/community/withdrawal/confirm.do?communityId=${community.id}" class="btn btn-default btn-sm">멤버탈퇴 신청하기</a>
                            </li>
                        </c:forEach>
                        <c:if test="${empty belongList}">
                            <li class="list-group-item">
                                <p class="text-center">가입된 커뮤니티가 없습니다.</p>
                            </li>
                        </c:if>
                    </ul>
                </div>

                <!-- ★★★ 미가입 커뮤니티 -->
                <div class="tab-pane fade" id="unjoinded">
                    <div class="page-header">
                        <h2 id="container">미가입 커뮤니티</h2>
                    </div>

                    <ul class="list-group">
                        <c:forEach var="community" items="${notBelongList}">
                            <li class="list-group-item">
                                <span class="badge"><fmt:formatDate value="${community.openDate}" pattern="yyyy-MM-dd"/></span>
                                <h4><span class="label label-info">추천</span>&nbsp;${community.name}</h4>
                                <p>${community.description}</p>
                                <a href="${ctx}/community/join/confirm.do?communityId=${community.id}" class="btn btn-default btn-sm">멤버 가입하기</a>
                            </li>
                        </c:forEach>
                        <c:if test="${empty notBelongList}">
                            <li class="list-group-item">
                                <p class="text-center">미가입된 커뮤니티가 없습니다.</p>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

<!-- Footer ========================================================================================== -->
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

</div>

</body>
</html>