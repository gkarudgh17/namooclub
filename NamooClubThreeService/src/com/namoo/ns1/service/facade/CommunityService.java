package com.namoo.ns1.service.facade;

import java.util.List;

import com.namoo.ns1.service.shared.exception.NamooRuntimeException;

import dom.entity.Community;
import dom.entity.CommunityMember;
import dom.entity.MembershipState;

public interface CommunityService {

	/**
	 * [주민으로 등록되지 않은 경우] 커뮤니티 개설
	 * <pre>
	 * 	주민 가입을 처리하고 나서 커뮤니티를 개설한다.
	 * 	이미 존재하는 주민인 경우 예외가 발생한다.
	 * </pre>
	 * @param communityName
	 * @param description
	 * @param adminName
	 * @param email
	 * @param password
	 * @return 커뮤니티 ID
	 * 
	 * @throws NamooRuntimeException
	 */
	public String registCommunity(String communityName, String description, String adminName, String email, String password);

	/**
	 * [주민으로 등록된 경우] 커뮤니티 개설
	 * <pre>
	 * 	이미 주민으로 가입되어 있는 경우 이메일만 필요하다.
	 * 	존재하지 않는 주민인 경우 예외가 발생한다.
	 * </pre> 
	 * 
	 * @param communityName
	 * @param description
	 * @param email
	 * @return 커뮤니티 ID
	 * 
	 * @throws NamooRuntimeException
	 */
	public String registCommunity(String communityName, String description, String email);

	/**
	 * 
	 * @param communityId
	 */
	public Community findCommunity(String communityId);

	/**
	 * [주민으로 등록되지 않은 경우] 커뮤니티 가입
	 * 
	 * 주민 가입을 처리하고 나서 커뮤니티에 가입한다.
	 * 이미 존재하는 주민인 경우 예외가 발생한다.
	 * 
	 * @param communityId
	 * @param name
	 * @param email
	 * @param password
	 * 
	 * @throws NamooRuntimeException
	 */
	public void joinAsMember(String communityId, String name, String email, String password);
	
	/**
	 * [주민으로 등록된 경우] 커뮤니티 가입
	 * 
	 * 이미 주민으로 가입되어 있는 경우 이메일만 필요하다.
	 * 존재하지 않는 주민인 경우 예외가 발생한다. 
	 * 
	 * @param communityId
	 * @param email
	 * 
	 * @throws NamooRuntimeException
	 */
	public void joinAsMember(String communityId, String email);

	/**
	 * @return
	 */
	public List<Community> findAllCommunities();
	
	
	/**
	 * 이메일로 커뮤니티 회원 찾기
	 * 
	 * @param communityId
	 * @param email
	 * @return
	 */
	public CommunityMember findCommunityMember(String communityId, String email);
	
	
	/**
	 * 커뮤니티 회원목록 조회
	 * 
	 * @param communityId
	 * @return
	 */
	public List<CommunityMember> findAllCommunityMember(String communityId);
	
	/**
	 * 
	 * @param communityId
	 */
	public int countMembers(String communityId);
	
	/**
	 * @param communityId
	 */
	public void removeCommunity(String communityId);
	
	/**
	 * 가입된 커뮤니티 목록조회
	 * 
	 * @param email
	 * @return
	 */
	public List<Community> findJoinedCommunities(String email);
	
	/**
	 * 미가입된 커뮤니티 목록조회
	 * 
	 * @param email
	 * @return
	 */
	public List<Community> findUnjoinedCommunities(String email);
	
	/**
	 * 자신이 관리하는 커뮤니티 목록조회
	 * 
	 * @param email
	 * @return
	 */
	public List<Community> findManagedCommnities(String email);

	/**
	 * 커뮤니티에서 탈퇴하기
	 * 
	 * @param communityId
	 * @param email
	 */
	public void withdrawCommunity(String communityId, String email);
	
	/**
	 * 커뮤니티 멤버의 상태를 업데이트한다.
	 * 
	 * @param communityId
	 * @param email
	 * @param state
	 */
	public void updateCommunityMemberState(String communityId, String email, MembershipState state);

	/**
	 * 커뮤티티정보를 수정한다.
	 * 
	 * @param community
	 */
	public void modifyCommunity(Community community);

}