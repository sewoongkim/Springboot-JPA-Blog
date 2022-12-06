package com.cos.blog.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
@RequiredArgsConstructor
public class BoardService {

	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;

	/*
	private UserRepository userRepository;
	private BoardRepository boardRepository;
	private ReplyRepository replyRepository;
	
	public BoardService(UserRepository uRepo, BoardRepository bRepo, ReplyRepository rRepo) {
		this.userRepository = uRepo;
		this.boardRepository = bRepo;
		this.replyRepository = rRepo;
	}
	*/
	/*
	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserRepository userRepository;
*/
	
	@Transactional
	public void 글쓰기(Board board, User user) { //title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	//public List<Board> 글목록(){
	//	return boardRepository.findAll();
	//}
	
    //Pageable pageable 
	public Page<Board> 글목록(Pageable pageable){
		//Page<Pageable> pagingList = boardRepository.findAll(pageable);
		// List<Pageable> lists = pagingList.getContent();
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다. ");
				});
	}
	
	@Transactional()
	public void 글삭제하기(int id) {
		 boardRepository.deleteById(id);
	}
	
	@Transactional()
	public void 글수정하기(int id, Board requestBoard) {
		Board board =boardRepository.findById(id) 
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다. ");
				});
				board.setTitle(requestBoard.getTitle());
				board.setContent(requestBoard.getContent());
				// 해당 함수 종료(Service 종료시)시에 트랜잭션이 종료됩니다.
				// 이 때 더팅체크 - 자동 업데이트가 됨.  db flush
	}

	@Transactional
	public void 댓글쓰기(User user, int boardId, Reply requestReply) {
		Board board =boardRepository.findById(boardId) 
				.orElseThrow(()->{
					return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
				});		
		requestReply.setUser(user);
		replyRepository.save(requestReply);
	}
	
	@Transactional
	public void 댓글쓰기1(ReplySaveRequestDto replySaveRequestDto ) {
		User user = userRepository.findById(replySaveRequestDto.getUserId())
				.orElseThrow(()->{
					return new IllegalArgumentException("댓글 쓰기 실패 : 사용자 id를 찾을 수 없습니다.");
				});	
		
		Board board =boardRepository.findById(replySaveRequestDto.getBoardId()) 
				.orElseThrow(()->{
					return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
				});	
		
		Reply reply = Reply.builder()
				.user(user)
				.board(board)
				.content(replySaveRequestDto.getContent())
				.build();
		
		replyRepository.save(reply);
	}

	@Transactional
	public void 댓글쓰기2(ReplySaveRequestDto replySaveRequestDto ) {
		int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
		System.out.println("BoardService Result : " + result);
	}
	@Transactional
	public void 댓글삭제(int replyId ) {
		replyRepository.deleteById(replyId);
	}
}
