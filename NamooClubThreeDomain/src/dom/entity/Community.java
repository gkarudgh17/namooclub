package dom.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.namoo.ns1.common.Identifiable;

public class Community implements Identifiable {

	private static final long serialVersionUID = -1649818789572216203L;
	
	private String id;
	private String name;
	private String description;
	private Date openDate;
	
	private CommunityManager manager;
	private List<CommunityMember> members;

	//--------------------------------------------------------------------------
	// constructors
	
	/**
	 * @param id
	 * @param communityName
	 * @param description
	 * @param admin
	 */
	public Community(String id, String communityName, String description, SocialPerson admin){
		//
		this.id = id;
		this.name = communityName;
		this.description = description;
		this.members = new ArrayList<CommunityMember>();
		this.openDate = new Date();
		
		setManager(admin);
		addMember(admin);
	}

	//--------------------------------------------------------------------------
	// getter/setter
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CommunityManager getManager() {
		return manager;
	}

	public List<CommunityMember> getMembers() {
		return members;
	}
	
	public Date getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	//--------------------------------------------------------------------------
	// public methods
	
	public CommunityMember findMember(String email) {
		//
		for (CommunityMember member : members) {
			if(member.getEmail().equals(email)) {
				return member;
			};
		}
		return null;
	}
	
	/**
	 * 
	 * @param rolePerson
	 */
	public void setManager(SocialPerson rolePerson){
		//
		CommunityManager manager = new CommunityManager(name, rolePerson);
		this.manager = manager;
	}

	/**
	 * 
	 * @param rolePerson
	 */
	public void addMember(SocialPerson rolePerson){
		//
		CommunityMember member = new CommunityMember(name, rolePerson);
		this.members.add(member);
	}

	@Override
	public String getOId() {
		// 
		return id;
	}

	public void removeMember(String email) {
		// 
		CommunityMember found = null;
		for (CommunityMember member : members) {
			if (member.getEmail().equals(email)) {
				found = member;
			}
		}
		if (found != null) {
			members.remove(found);
		}
	}

	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder();
		builder.append("id:" + id);
		builder.append(",name:" + name);
		builder.append(",email:" + description);
		
		return builder.toString();
	}
}