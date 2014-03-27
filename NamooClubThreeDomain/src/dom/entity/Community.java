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
	private List<Category> clubCategories;
	
	private CommunityManager manager;
	private List<CommunityMember> members;
	private List<Club> clubs;

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
		this.openDate = new Date();
		
		this.members = new ArrayList<CommunityMember>();
		this.clubs = new ArrayList<Club>();
		this.clubCategories = new ArrayList<Category>();
		
		setManager(admin);
		addMember(admin, MembershipState.Active);
		
	}
	
	//--------------------------------------------------------------------------
	// methods

	public CommunityMember findMember(String email) {
		//
		for (CommunityMember member : members) {
			if(member.getEmail().equals(email)) {
				return member;
			};
		}
		return null;
	}
	
	public void setManager(SocialPerson rolePerson){
		//
		CommunityManager manager = new CommunityManager(id, rolePerson);
		this.manager = manager;
	}

	public void addMember(SocialPerson rolePerson){
		//
		CommunityMember member = new CommunityMember(id, rolePerson);
		this.members.add(member);
	}
	
	public void addMember(SocialPerson rolePerson, MembershipState state){
		//
		CommunityMember member = new CommunityMember(id, rolePerson);
		member.setState(state);
		this.members.add(member);
	}

	public void addClub(Club club) {
		// 
		this.clubs.add(club);
		
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
	
	public void removeClub(String clubId) {
		// 
		Club found = null;
		for (Club club : clubs) {
			if (club.getId().equals(clubId)) {
				found = club;
				break;
			}
		}
		if (found != null) {
			clubs.remove(found);
		}
	}	

	public void addClubCategory(Category category) {
		//
		this.clubCategories.add(category);
	}
	
	public Category findClubCategoryById(String categoryId) {
		// 
		for (Category category : clubCategories) {
			if (category.getId().equals(categoryId)) {
				return category;
			}
		}
		return null;
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
	
	public List<Club> getClubs() {
		return clubs;
	}
	
	public List<Category> getClubCategories() {
		return clubCategories;
	}

	//--------------------------------------------------------------------------
	// override

	@Override
	public String getOId() {
		// 
		return id;
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