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

            <c:choose>
              <c:when test="${not empty communityId}">
                <!-- ★★★ Tab Menu -->
                <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                    <li class="active"><a href="#all" data-toggle="tab">전체회원</a></li>
                    <li class=""><a href="#join" data-toggle="tab">가입신청 회원</a></li>
                    <li class=""><a href="#withdraw" data-toggle="tab">탈퇴신청 회원</a></li>
                </ul>

                <!-- ★★★ Tab Panel -->
                <div id="communityList" class="tab-content">
                    <!-- ★★★ 전체회원 -->
                    <div class="tab-pane fade active in" id="all">
    
                        <!-- ★★★ 회원목록 -->
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="text-center">번호</th>
                                    <th class="text-center">ID</th>
                                    <th class="text-center">회원명</th>
                                    <th class="text-center">상태</th>
                                    <th class="text-center">가입 클럽수</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="member" items="${members}" varStatus="status">
                                  <tr>
                                      <td class="text-center">${status.count}</td>
                                      <td>${member.email}</td>
                                      <td class="text-center"><a href="${ctx}/management/member/detail.do?communityId=${communityId}&email=${member.email}">${member.name}</a></td>
                                      <td class="text-center">${member.state.name}</td>
                                      <td class="text-center">${member.countOfClubs}</td>
                                  </tr>
                                </c:forEach>
                                <c:if test="${empty members}">
                                  <tr><td colspan="5" class="text-center">가입한 회원이 없습니다.</td></tr>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
    
                    </div>
    
                    <!-- ★★★ 가입신청 회원 -->
                    <div class="tab-pane fade" id="join">
                        <!-- ★★★ 회원목록 -->
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="text-center">번호</th>
                                    <th class="text-center">ID</th>
                                    <th class="text-center">회원명</th>
                                    <th class="text-center">가입승인</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="count" value="0" />
                                <c:forEach var="member" items="${members}" varStatus="status"> 
                                  <c:if test="${member.state == 'Requested'}">
                                    <c:set var="count" value="${count+ 1}" />
                                    <tr>
                                        <td class="text-center">${status.count}</td>
                                        <td>${member.email}</td>
                                        <td><a href="${ctx}/management/member/detail.do?communityId=${communityId}&email=${member.email}">${member.name}</a></td>
                                        <td class="text-center">
                                            <c:if test="${member.authorized}">
                                              <a href="${ctx}/management/member/list.do?communityId=${communityId}&type=accept&email=${member.email}" class="btn btn-success btn-xs">가입승인</a>
                                              <a href="${ctx}/management/member/list.do?communityId=${communityId}&type=reject&email=${member.email}" class="btn btn-default btn-xs">가입거절</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                  </c:if>
                                </c:forEach>
                                <c:if test="${count == 0}">
                                  <tr><td colspan="4" class="text-center">가입 신청한 회원이 없습니다.</td></tr>
                                </c:if>
                                
                                </tbody>
                            </table>
                        </div>
    
                    </div>
    
                    <!-- ★★★ 탈퇴신청 회원 -->
                    <div class="tab-pane fade" id="withdraw">
                        <!-- ★★★ 회원목록 -->
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="text-center">번호</th>
                                    <th class="text-center">ID</th>
                                    <th class="text-center">회원명</th>
                                    <th class="text-center">탈퇴승인</th>
                                </tr>
                                </thead>
                                <tbody>
                                
                                <c:set var="count" value="0" />
                                <c:forEach var="member" items="${members}" varStatus="status"> 
                                  <c:if test="${member.state == 'RequestWithdrawal'}">
                                    <c:set var="count" value="${count+ 1}" />
                                    <tr>
                                        <td class="text-center">${status.count}</td>
                                        <td>${member.email}</td>
                                        <td><a href="${ctx}/management/member/detail.do?communityId=${communityId}&email=${member.email}">${member.name}</a></td>
                                        <td class="text-center">
                                          <c:if test="${member.authorized}">
                                            <a href="${ctx}/management/member/list.do?communityId=${communityId}&type=withdraw&email=${member.email}" class="btn btn-success btn-xs">탈퇴승인</a>
                                          </c:if>
                                        </td>
                                    </tr>
                                  </c:if>
                                </c:forEach>
                                <c:if test="${count == 0}">
                                  <tr><td colspan="4" class="text-center">탈퇴 신청한 회원이 없습니다.</td></tr>
                                </c:if>                            
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
              </c:when>
              <c:otherwise>
                <ul class="list-group">
                  <li class="list-group-item">
                      <p class="text-center">커뮤니티를 먼저 선택하세요.</p>
                  </li>
                </ul>                
              </c:otherwise>
            </c:choose>

        </div>
    </div>
<!-- Footer ========================================================================================== -->
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

</div>

</body>
</html>