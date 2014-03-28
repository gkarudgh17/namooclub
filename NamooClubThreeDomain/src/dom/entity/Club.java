package dom.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.namoo.ns1.common.Identifiable;

public class Club implements Identifiable {
	//
	private static final long serialVersionUID = 3066633976084014659L;
	
	private String id;
	private String name;
	private String description;
	private Category category;
	private Date openDate;
	private ClubState state;
	
	private List<ClubManager> managers;
	private List<ClubMember> members;

	//--------------------------------------------------------------------------
	
	public Club(String id, String name, String description, Category category, SocialPerson admin) {
		//
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.openDate = new Date();
		this.state = ClubState.Requested;
		
		this.managers = new ArrayList<ClubManager>();
		this.members = new ArrayList<ClubMember>();
		
		addManager(admin);
		addMember(admin, MembershipState.Active);
	}
	
	//--------------------------------------------------------------------------
	// methods
	
	public void addMember(SocialPerson admin) {
		// 
		addMember(admin, MembershipState.Requested);
	}
	
	private void addMember(SocialPerson admin, MembershipState state) {
		// 
		this.members.add(new ClubMember(id, admin, state));
	}
	
	public void addManager(SocialPerson admin) {
		// 
		boolean representative = false;
		if (this.managers.size() == 0) {
			representative = true;
		}
		this.managers.add(new ClubManager(id, admin, representative));
	}
	
	public ClubManager findManager(String email) {
		//
		for (ClubManager manager : managers) {
			if (manager.getEmail().equals(email)) {
				return manager;
			}
		}
		return null;
	}
	
	public ClubMember findMember(String email) {
		//
		for (ClubMember member : members) {
			if (member.getEmail().equals(email)) {
				return member;
			}
		}
		return null;
	}
	
	public void removeMember(String email) {
		// 
		ClubMember found = null;
		for (ClubMember member : members) {
			if (member.getEmail().equals(email)) {
				found = member;
				break;
			}
		}
		if (found != null) {
			members.remove(found);
		}
	}
	
	public ClubManager getRepresentativeManager() {
		// 
		for (ClubManager manager : managers) {
			if (manager.isRepresentative()) {
				return manager;
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

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public ClubState getState() {
		return state;
	}

	public void setState(ClubState state) {
		this.state = state;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ClubManager> getManagers() {
		return managers;
	}

	public List<ClubMember> getMembers() {
		return members;
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
		builder.append(",description:" + description);
		builder.append(",openDate:" + openDate);
		
		return builder.toString();
	}
}
