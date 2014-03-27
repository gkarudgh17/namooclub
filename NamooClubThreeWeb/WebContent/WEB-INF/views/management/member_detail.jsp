<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>나무커뮤니티</title>
<%@ include file="/WEB-INF/views/layout/common.jsp" %>
<script src="${ctx}/resources/management/js/management.js" type="text/javascript"></script>

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
                    <h2>커뮤니티 관리센터</h2>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12 col-lg-12">
                <ol class="breadcrumb">
                    <li><a href="#">관리자 홈</a></li>
                    <li><a href="#">커뮤니티 관리</a></li>
                    <li class="active">회원관리</li>
                </ol>
            </div>
        </div>
    </div>
</header>

<!-- Container ======================================================================================= -->

<div class="container">
    <div class="row">
        
        <!-- ★★★ Aside Menu -->
        <c:set var="activeMenu" value="member" />
        <%@ include file="/WEB-INF/views/management/aside_menu.jsp" %>

        <!-- ★★★ Contents -->
        <div class="col-sm-9 col-lg-9">
            <div class="page-header2">
                <h3>회원관리</h3>
            </div>

              <div class="table-responsive">
                <table class="table table-bordered">
                    <colgroup>
                        <col width="120">
                        <col width="*">
                        <col width="120">
                        <col width="*">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th class="text-center">ID</th>
                        <td class="text-left">${member.email}</td>
                        <th class="text-center">회원명</th>
                        <td class="text-left">${member.name}</td>
                    </tr>
                    <tr>
                        <th class="text-center">생년월일</th>
                        <td class="text-left"><fmt:formatDate value="${member.birthDate}" pattern="yyyy년 MM월 dd일"/></td>
                        <th class="text-center">성별</th>
                        <td class="text-left">${member.gender.name}</td>
                    </tr>
                    <tr>
                        <th class="text-center">가입일</th>
                        <td class="text-left"><fmt:formatDate value="${member.joinDate}" pattern="yyyy-MM-dd"/></td>
                        <th class="text-center">가입상태</th>
                        <td class="text-left">${member.state.name}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <h4>가입 클럽</h4>
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="text-center">가입클럽</th>
                        <th class="text-center">클럽운영자</th>
                        <th class="text-center">클럽가입일</th>
                        <th class="text-center">가입상태</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="club" items="${member.clubs}">
                      <tr>
                          <td>${club.name}</td>
                          <td class="text-center"><c:choose><c:when test="${club.owned}">예</c:when><c:otherwise>아니오</c:otherwise></c:choose></td>
                          <td class="text-center">${club.joinDate}</td>
                          <td class="text-center">${club.state.name}</td>
                      </tr>
                    </c:forEach>
                    <c:if test="${empty member.clubs}">
                      <tr><td colspan="4" class="text-center">가입한 클럽이 없습니다.</td></tr>
                    </c:if>
                    </tbody>
                </table>
            </div>

            <div class="pull-right">
              <c:if test="${member.state == 'Requested'}">
                <a href="${ctx}/management/member/list.do?communityId=${communityId}&type=accept&email=${member.email}" class="btn btn-success">가입승인</a>
                <a href="${ctx}/management/member/list.do?communityId=${communityId}&type=reject&email=${member.email}" class="btn btn-default">가입거절</a>
              </c:if> 
              <c:if test="${member.state == 'RequestWithdrawal'}">
                <a href="${ctx}/management/member/list.do?communityId=${communityId}&type=withdraw&email=${member.email}" class="btn btn-default">탈퇴승인</a>
              </c:if> 
              <c:if test="${member.state != 'Closed'}">
                <a href="${ctx}/management/member/list.do?communityId=${communityId}&type=withdraw&email=${member.email}" class="btn btn-default">강제탈퇴</a>
              </c:if> 
              <a href="${ctx}/management/member/list.do?communityId=${communityId}" class="btn btn-default">목록</a>
            </div>

        </div>
    </div>
<!-- Footer ========================================================================================== -->
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

</div>

</body>
</html>