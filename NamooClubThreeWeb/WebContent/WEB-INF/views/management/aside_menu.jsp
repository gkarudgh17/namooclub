<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ★★★ Aside Menu -->
<div style="z-index:1020" class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
    <div class="list-group panel panel-success">
        <div class="panel-heading list-group-item text-center hidden-xs">
            <h4>커뮤니티 관리</h4>
        </div>
        <div id="cat-navi">
            <select class="form-control" onchange="reloadPage();" id="community">
                <option selected="selected" value="">:: 커뮤니티 선택 ::</option>
                <c:forEach var="community" items="${communities}">
                    <option value="${community.id}" <c:if test="${communityId == community.id}">selected="selected"</c:if>>${community.name}</option>
                </c:forEach>
            </select>
            <a href="${ctx}/management/club.do?communityId=${communityId}" class="list-group-item <c:if test="${activeMenu=='club'}">active</c:if>">클럽관리</a>
            <a href="${ctx}/management/member.do?communityId=${communityId}" class="list-group-item <c:if test="${activeMenu=='member'}">active</c:if>">회원관리</a>
        </div>
        <div class="panel-footer">
            <div class="row">
                <div class="col-xs-6 col-sm-12 col-md-6 col-lg-6 text-left">
                    <a class="btn btn-link btn-sm btn-block" href="#">클럽운영자 채팅</a>
                </div>
                <div class="col-xs-6 col-sm-12 col-md-6 col-lg-6 text-left">
                    <a class="btn btn-link btn-sm btn-block" href="#">전체 메일발송</a>
                </div>
            </div>
        </div>
    </div>
</div>
