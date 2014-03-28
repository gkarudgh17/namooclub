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
        <c:set var="activeMenu" value="club" />
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
                    <li class="active"><a href="#all" data-toggle="tab">전체클럽</a></li>
                    <li class=""><a href="#open" data-toggle="tab">개설신청 클럽</a></li>
                    <li class=""><a href="#close" data-toggle="tab">폐쇄신청 클럽</a></li>
                </ul>

                <!-- ★★★ Tab Panel -->
                <div id="communityList" class="tab-content">
                    <!-- ★★★ 전체회원 -->
                    <div class="tab-pane fade active in" id="all">
    
                        <!-- ★★★ 클럽목록 -->
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="text-center">번호</th>
                                    <th class="text-center">카테고리</th>
                                    <th class="text-center">클럽명</th>
                                    <th class="text-center">상태</th>
                                    <th class="text-center">대표운영자</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="club" items="${clubs}" varStatus="status">
                                  <tr>
                                      <td class="text-center">${status.count}</td>
                                      <td>${club.category}</td>
                                      <td>${club.name}</td>
                                      <td class="text-center">${club.state.name}</td>
                                      <td>${club.manager.name}(${club.manager.email})</td>
                                  </tr>
                                </c:forEach>
                                <c:if test="${empty clubs}">
                                  <tr><td colspan="5" class="text-center">개설된 클럽이 없습니다.</td></tr>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
    
                    </div>
    
                    <!-- ★★★ 개설신청 클럽 -->
                    <div class="tab-pane fade" id="open">
                        <!-- ★★★ 클럽목록 -->
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="text-center">번호</th>
                                    <th class="text-center">카테고리</th>
                                    <th class="text-center">클럽명</th>
                                    <th class="text-center">대표운영자</th>
                                    <th class="text-center">개설승인</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="count" value="0" />
                                <c:forEach var="club" items="${clubs}" varStatus="status"> 
                                  <c:if test="${club.state == 'Requested'}">
                                    <c:set var="count" value="${count+ 1}" />
                                    <tr>
                                        <td class="text-center">${status.count}</td>
                                        <td>${club.category}</td>
                                        <td>${club.name}</td>
                                        <td>${club.manager.name}(${club.manager.email})</td>
                                        <td class="text-center">
                                            <c:if test="${club.authorized}">
                                                <a href="${ctx}/management/club/list.do?communityId=${communityId}&type=accept&clubId=${club.id}" class="btn btn-success btn-xs">개설승인</a>
                                                <a href="${ctx}/management/club/list.do?communityId=${communityId}&type=reject&clubId=${club.id}" class="btn btn-default btn-xs">개설거절</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                  </c:if>
                                </c:forEach>
                                <c:if test="${count == 0}">
                                  <tr><td colspan="5" class="text-center">개설 신청한 클럽이 없습니다.</td></tr>
                                </c:if>
                                
                                </tbody>
                            </table>
                        </div>
    
                    </div>
                    <!-- ★★★ 폐쇄신청 클럽 -->
                    <div class="tab-pane fade" id="close">
                        <!-- ★★★ 클럽목록 -->
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="text-center">번호</th>
                                    <th class="text-center">카테고리</th>
                                    <th class="text-center">클럽명</th>
                                    <th class="text-center">대표운영자</th>
                                    <th class="text-center">폐쇄승인</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="count" value="0" />
                                <c:forEach var="club" items="${clubs}" varStatus="status"> 
                                  <c:if test="${club.state == 'RequestClose'}">
                                    <c:set var="count" value="${count+ 1}" />
                                    <tr>
                                        <td class="text-center">${status.count}</td>
                                        <td>${club.category}</td>
                                        <td>${club.name}</td>
                                        <td>${club.manager.name}(${club.manager.email})</td>
                                        <td class="text-center">
                                          <c:if test="${club.authorized}">
                                              <a href="${ctx}/management/club/list.do?communityId=${communityId}&type=close&clubId=${club.id}" class="btn btn-success btn-xs">폐쇄승인</a>
                                          </c:if>
                                        </td>
                                    </tr>
                                  </c:if>
                                </c:forEach>
                                <c:if test="${count == 0}">
                                  <tr><td colspan="5" class="text-center">폐쇄 신청한 클럽이 없습니다.</td></tr>
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
