package com.example.tobi.springbootbasicboard.service;

import com.example.tobi.springbootbasicboard.mapper.BoardMapper;
import com.example.tobi.springbootbasicboard.model.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    void selectBoardDetail_테스트() {
        // given
        Long boardId = 36L;

        // when
        Board board = boardMapper.selectBoardDetail(boardId);

        // then
        System.out.println(board);

    }

}