
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.InvitationReceiveModel;
import java.util.Date;

public interface InvitationReceiveService{
	
	public int create(InvitationReceiveModel invitationReceiveModel);
	
	public int createSelective(InvitationReceiveModel invitationReceiveModel);
	
	public InvitationReceiveModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(InvitationReceiveModel invitationReceiveModel);
	
	public int updateByPrimaryKeySelective(InvitationReceiveModel invitationReceiveModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(InvitationReceiveModel invitationReceiveModel);
	
}