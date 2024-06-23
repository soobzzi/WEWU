/*
 * 작성자 : 추경운
 * 작성일 : 2024-06-03
 */
package life.wewu.web.controller.group;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.apache.bcel.classfile.Module.Require;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;

import life.wewu.web.common.Search;
import life.wewu.web.domain.board.Board;
import life.wewu.web.domain.group.Group;
import life.wewu.web.domain.group.GroupAcle;
import life.wewu.web.domain.group.GroupBoard;
import life.wewu.web.domain.group.GroupMember;
import life.wewu.web.service.active.ActiveService;
import life.wewu.web.service.board.BoardService;
import life.wewu.web.service.group.GroupService;

@RestController
@RequestMapping("/app/group/*")
public class GroupRestController {

	///필드
	@Autowired
	@Qualifier("groupService")
	private GroupService groupService;
	
	@Autowired
	@Qualifier("activeServiceImpl")
	private ActiveService activeService;
	
	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;
	
	@Value("${map.clientId}")
	private String clientId;
	
	public GroupRestController()
	{
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="getGroupList",method = RequestMethod.POST)
	public List<Group> getGroupList(@RequestBody Search search) throws Exception 
	{
		System.out.println(":: /app/group/getGroupList ::");
		System.out.println(search);
		System.out.println("return Data :: " + groupService.getGroupList(search));
		// Business logic 수행
		return groupService.getGroupList(search);
	}
	
	@RequestMapping(value="getGroupRankingList",method = RequestMethod.POST)
	public List<Group> getGroupRankingList(@RequestBody Search search) throws Exception 
	{
		System.out.println(":: /app/group/getGroupRankingList ::");
		System.out.println(search);
		System.out.println("return Data :: " + groupService.getGroupRankingList(search));
		// Business logic 수행
		return groupService.getGroupRankingList(search);
	}
	
	@RequestMapping(value="getApplJoinList",method = RequestMethod.POST)
	public List<Object> getApplJoinList(@RequestBody Search search) throws Exception 
	{
		System.out.println(":: /app/group/getApplJoinList ::");
		// Business logic 수행
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("search", search);
		List<Object> list = new ArrayList<Object>();
		for(GroupMember groupMember : groupService.getMemberGroupList(map))
		{
			Group group = groupService.getGroup(groupMember.getGroupNo());
			list.add(group);
			list.add(groupMember);
		}
		
		System.out.println(search);
		System.out.println(list);
		// Business logic 수행
		return list;
	}
	
	@RequestMapping(value="updateGroupRslt",method = RequestMethod.POST)
	public Group updateGroupRslt(@RequestBody Group group) throws Exception 
	{
		System.out.println(":: /app/group/updateGroupRslt ::");
		
		// Business logic 수행
		return groupService.updateGroupRslt(group);
	}
	
	@RequestMapping(value="getMemberGroupList",method = RequestMethod.POST)
	public List<GroupMember> getMemberGroupList(@RequestBody Map<String, Object> requestData) throws Exception 
	{
		System.out.println(":: /app/group/getMemberGroupList ::");
		
		String searchCondition = (String) requestData.get("searchCondition");
	    String searchKeyword = (String) requestData.get("searchKeyword");
	    String joinFlag = (String) requestData.get("joinFlag");
		Search search = new Search();
		search.setSearchCondition(searchCondition);
		search.setSearchKeyword(searchKeyword);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("search", search);
		map.put("joinFlag", joinFlag);
		System.out.println(search);
		System.out.println(joinFlag);
		// Business logic 수행
		return groupService.getMemberGroupList(map);
	}
	
	//지원이 사용해야 함
	@RequestMapping(value="getUserGroupList",method = RequestMethod.POST)
	public List<Group> getUserGroupList(@RequestBody String nickname) throws Exception 
	{
		System.out.println(":: /app/group/getMemberGroupList ::");
		
		String searchCondition = "user";
	    String searchKeyword = nickname;
	    
		Search search = new Search();
		search.setSearchKeyword(searchCondition);
		search.setSearchKeyword(searchKeyword);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("search", search);
		List<GroupMember> list = groupService.getMemberGroupList(map);
		List<Group> group = new ArrayList<Group>();
		for(GroupMember groupMember : list)
		{
			group.add(groupService.getGroup(groupMember.getGroupNo()));
		}
		
		// Business logic 수행
		return group;
	}
	
	@RequestMapping(value="getScrab",method = RequestMethod.POST)
	public String getScrab(@RequestBody Map<String, Object> requestData) throws Exception 
	{
		System.out.println(":: /app/group/getScrab ::");
		
		int memberNo = (int)requestData.get("memberNo");;
		
		// Business logic 수행
		return groupService.getScrab(memberNo);
	}
	
	@RequestMapping(value="updateScrab",method = RequestMethod.POST)
	public GroupMember updateScrab(@RequestBody GroupMember groupMember) throws Exception 
	{
		System.out.println(":: /app/group/updateScrab ::");
		
		// Business logic 수행
		return groupService.updateScrab(groupMember);
	}
	
	@RequestMapping(value="updateGroup",method = RequestMethod.POST)
	public Group updateGroup(@RequestBody Group group) throws Exception 
	{
		System.out.println(":: /app/group/updateGroup ::");
		// Business logic 수행
		group = groupService.updateGroup(group);
		
		return group;
	}
	
	@RequestMapping(value="deleteGroup",method = RequestMethod.POST)
	public String deleteGroup(@RequestBody Map<String, Object> rslt) throws Exception 
	{
		System.out.println(":: /app/group/deleteGroup ::");
		// Business logic 수행
		String flag = "";
		int groupNo = Integer.parseInt((String)rslt.get("groupNo"));
		
		try {
			groupService.deleteGroup(groupNo);
			flag = "Y";
		}catch(Exception e)
		{
			System.out.println(e);
			flag = "N";
		}
		
		// JSON 형식의 응답 반환
        return "{\"flag\": \"" + flag + "\"}";
	}
	
	@RequestMapping(value="addApplGroup",method = RequestMethod.POST)
	public Group addApplGroup(@RequestBody Group group) throws Exception 
	{
		System.out.println(":: /app/group/addApplGroup ::");
		// Business logic 수행
		groupService.addGroup(group);
		System.out.println(group);
        return group;
	}
	
	@RequestMapping(value="updateApplGroup",method = RequestMethod.POST)
	public Group updateApplGroup(@RequestBody Group group) throws Exception 
	{
		System.out.println(":: /app/group/updateApplGroup ::");
		// Business logic 수행
		group = groupService.updateGroup(group);
        return group;
	}
	
	@RequestMapping(value="deleteApplGroup",method = RequestMethod.POST)
	public String deleteApplGroup(@RequestBody Map<String, Object> rslt) throws Exception 
	{
		System.out.println(":: /app/group/deleteApplGroup ::");
		// Business logic 수행
		String flag = "";
		int groupNo = Integer.parseInt((String)rslt.get("groupNo"));
		System.out.println(groupNo);
		
		try {
			groupService.deleteGroup(groupNo);
			flag = "Y";
		}catch(Exception e)
		{
			Throwable cause = e.getCause();

			if (cause.getMessage().contains("board_type")) {
			    System.out.println("모임에 생성된 게시판 있음");
			    flag = "board";
			} else if(cause.getMessage().contains("user")){
			    System.out.println("모임에 남은 모임원이 있음");
			    flag = "user";
			} else
			{
				flag = "N";
			}
			
		}
		
		// JSON 형식의 응답 반환
        return "{\"flag\": \"" + flag + "\"}";
	}
	
	@RequestMapping(value="addApplJoin",method = RequestMethod.POST)
	public GroupMember addApplJoin(@RequestBody GroupMember groupMember) throws Exception 
	{
		
		System.out.println(":: /group/addApplJoin ::");
		
		// Business logic 수행
		
		try {
			groupService.addMemberGroup(groupMember);
		}catch(Exception e)
		{
			Throwable cause = e.getCause();
			System.out.println(cause.getMessage());
			
		}
		// JSON 형식의 응답 반환
        return groupMember;
	}
	
	@RequestMapping(value="deleteApplJoin",method = RequestMethod.POST)
	public String deleteApplJoin(@RequestBody Map<String, Object> rslt) throws Exception 
	{
		System.out.println(":: /app/group/deleteApplJoin ::");
		// Business logic 수행
		String flag = "";
		int memberNo = Integer.parseInt((String)rslt.get("memberNo"));
		System.out.println(memberNo);
		
		try {
			groupService.deleteMemberGroup(memberNo);
			flag = "Y";
		}catch(Exception e)
		{
			Throwable cause = e.getCause();

		    System.out.println(cause);
			flag = "N";
		}
		
		// JSON 형식의 응답 반환
        return "{\"flag\": \"" + flag + "\"}";
	}
	
	@RequestMapping(value="updateApplJoin",method = RequestMethod.POST)
	public String updateApplJoin(@RequestBody GroupMember groupMember) throws Exception 
	{
		System.out.println(":: /app/group/updateApplJoin ::");
		// Business logic 수행
		System.out.println(groupMember);
		String flag = "";
		try {
			groupMember = groupService.updateApplJoin(groupMember);
			flag = "Y";
		}catch(Exception e)
		{
			Throwable cause = e.getCause();

		    System.out.println(cause);
			flag = "N";
		}
		
		// JSON 형식의 응답 반환
        return "{\"flag\": \"" + flag + "\"}";
	}
	
	@RequestMapping(value="updateApplJoinForm",method = RequestMethod.POST)
	public Group updateApplJoinForm(@RequestBody Group group) throws Exception 
	{
		System.out.println(":: /app/group/updateApplJoinForm ::");
		// Business logic 수행
		System.out.println(group.getGroupNo());
		System.out.println(group.getFrstQuest());
		group = groupService.updateGroup(group);
		
		return group;
	}
	
	@RequestMapping(value="addGroupBoard",method = RequestMethod.POST)
	public GroupBoard addGroupBoard(@RequestBody GroupBoard groupBoard) throws Exception 
	{
		System.out.println(":: /app/group/addGroupBoard ::");
		// Business logic 수행
		groupService.addGroupBoard(groupBoard);
		
		return groupBoard;
	}
	
	@RequestMapping(value="getGroupBoardList",method = RequestMethod.POST)
	public List<GroupBoard> getGroupBoardList(@RequestBody Map<String, Object> rslt) throws Exception 
	{
		System.out.println(":: /app/group/getGroupBoardList ::");
		
		int groupNo = Integer.parseInt((String)rslt.get("groupNo"));
		
		// Business logic 수행
		
		return groupService.getGroupBoardList(groupNo);
	}
	
	@RequestMapping(value="updateGroupBoard",method = RequestMethod.POST)
	public GroupBoard updateGroupBoard(@RequestBody GroupBoard groupBoard) throws Exception 
	{
		System.out.println(":: /app/group/updateGroupBoard ::");
		// Business logic 수행
		groupBoard = groupService.updateGroupBoard(groupBoard);
		
		return groupBoard;
	}
	
	@RequestMapping(value="deleteGroupBoard",method = RequestMethod.POST)
	public String deleteGroupBoard(@RequestBody Map<String, Object> rslt) throws Exception 
	{
		System.out.println(":: /app/group/deleteGroupBoard ::");
		// Business logic 수행
		String flag = "";
		int typeNo = Integer.parseInt((String)rslt.get("typeNo"));
		
		try {
			groupService.deleteGroupBoard(typeNo);
			flag = "Y";
		}catch(Exception e)
		{
			Throwable cause = e.getCause();

		    System.out.println(cause);
			flag = "N";
		}
		
		// JSON 형식의 응답 반환
        return "{\"flag\": \"" + flag + "\"}";
	}
	
	@RequestMapping(value="deleteMemberGroup",method = RequestMethod.POST)
	public String deleteMemberGroup(@RequestBody Map<String, Object> rslt) throws Exception 
	{
		System.out.println(":: /app/group/deleteGroupMember ::");
		// Business logic 수행
		String flag = "";
		int memberNo = Integer.parseInt((String)rslt.get("memberNo"));
		
		try {
			groupService.deleteMemberGroup(memberNo);
			flag = "Y";
		}catch(Exception e)
		{
			Throwable cause = e.getCause();

		    System.out.println(cause);
			flag = "N";
		}
		
		// JSON 형식의 응답 반환
        return "{\"flag\": \"" + flag + "\"}";
	}
	
	@RequestMapping(value="updateMemberGroup",method = RequestMethod.POST)
	public GroupMember updateMemberGroup(@RequestBody GroupMember groupMember) throws Exception 
	{
		System.out.println(":: /app/group/updateMemberGroup ::");
		System.out.println(groupMember);
		
		// Business logic 수행
		groupMember = groupService.updateMemberGroup(groupMember);
		
		return groupMember;
	}
	
	@RequestMapping(value="addGroupAcle",method = RequestMethod.POST)
	public GroupAcle addGroupAcle(@RequestBody GroupAcle groupAcle) throws Exception 
	{
		System.out.println(":: /app/group/addGroupAcle ::");
		System.out.println(groupAcle);
		// Business logic 수행
		groupAcle = groupService.addGroupAcle(groupAcle);
		
		return groupAcle;
	}
	
	@RequestMapping(value="deleteGroupAcle",method = RequestMethod.POST)
	public String deleteGroupAcle(@RequestBody Map<String, Object> rslt) throws Exception 
	{
		System.out.println(":: /app/group/deleteGroupAcle ::");
		// Business logic 수행
		String flag = "";
		int boardNo = Integer.parseInt((String)rslt.get("boardNo"));
		
		try {
			groupService.deleteGroupAcle(boardNo);
			flag = "Y";
		}catch(Exception e)
		{
			Throwable cause = e.getCause();

		    System.out.println(cause);
			flag = "N";
		}
		
		// JSON 형식의 응답 반환
        return "{\"flag\": \"" + flag + "\"}";
	}
	
	@RequestMapping(value="updateGroupAcle",method = RequestMethod.POST)
	public GroupAcle updateGroupAcle(@RequestBody GroupAcle groupAcle) throws Exception 
	{
		System.out.println(":: /app/group/updateGroupAcle ::");
		
		// Business logic 수행
		groupAcle = groupService.updateGroupAcle(groupAcle);
		
		return groupAcle;
	}
	
	@RequestMapping(value="getAcleList",method = RequestMethod.POST)
	public List<GroupAcle> getAcleList(@RequestBody Map<String, Object> rslt) throws Exception 
	{
		System.out.println(":: /app/group/getAcleList ::");
		int typeNo = (int)rslt.get("typeNo");
		
		Search search = new Search();
		search.setCurrentPage(1); // 0
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("boardType", typeNo);
		
		// Business logic 수행
		List<Board> boardList = boardService.getBoardList(map);
		List<GroupAcle> acleList = new ArrayList<GroupAcle>();
		
		for(Board board : boardList){
			GroupAcle groupAcle = GroupAcle.builder()
					.boardNo(board.getBoardNo())
					.typeNo(board.getBoardType())
					.wrteName(board.getNickName())
					.acleName(board.getTitle())
					.acleContents(board.getContents())
					.wrteDate(board.getRegDate())
					.build();
			acleList.add(groupAcle);
		}
		System.out.println(acleList);
		return acleList;
	}
	
	@GetMapping(value = "activeMap")
	public Map<String,Object> activeMap() throws Exception {
		
		System.out.println("activeMap");
		
		Search search = new Search();
		search.setSearchCondition("T");
		search.setCurrentPage(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activeList", activeService.getActiveList(search));
		map.put("clientId", clientId);
		return map;
	}

	
	//오리날다............덕슨날다.....
}
