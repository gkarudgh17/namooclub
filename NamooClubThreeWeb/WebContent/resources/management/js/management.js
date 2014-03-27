/**
 * 커뮤니티 관리센터 공통 스크립트
 */
function reloadPage() {
	//
	var communityId = document.getElementById("community").value;
	var url = window.location.href.split('?')[0];
	if (communityId) {
		url += "?communityId=" + communityId;
	}
	
	window.location.href = url;
}