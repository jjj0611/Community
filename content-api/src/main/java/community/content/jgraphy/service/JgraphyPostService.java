package community.content.jgraphy.service;

import community.content.jgraphy.api.dto.JgraphyPostRequestDto;
import community.content.jgraphy.api.dto.JgraphyPostResponseDto;
import community.content.jgraphy.domain.JgraphyPost;
import community.content.jgraphy.domain.JgraphyPostRepository;
import community.content.jgraphy.exception.JgraphyPostNotFoundException;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JgraphyPostService {
  private final JgraphyPostRepository jgraphyPostRepository;

  public Page<JgraphyPostResponseDto> readPostPage(Pageable pageable) {
    return jgraphyPostRepository.findAll(pageable).map(JgraphyPostResponseDto::from);
  }

  public Long createPost(JgraphyPostRequestDto jgraphyPostRequestDto) {
    return jgraphyPostRepository.save(JgraphyPostRequestDto.from(jgraphyPostRequestDto)).getId();
  }

  public JgraphyPostResponseDto readPost(Long id) {
    JgraphyPost jgraphyPost = findJgraphyPostById(id);
    return JgraphyPostResponseDto.from(jgraphyPost);
  }

  public void deletePost(Long id) {
    JgraphyPost jgraphyPost = findJgraphyPostById(id);
    jgraphyPostRepository.delete(jgraphyPost);
  }

  @Transactional
  public void updatePost(Long id, JgraphyPostRequestDto jgraphyPostRequestDto) {
    JgraphyPost jgraphyPost = findJgraphyPostById(id);
    JgraphyPost updateValue = JgraphyPostRequestDto.from(jgraphyPostRequestDto);
    jgraphyPost.update(updateValue);
  }

  private JgraphyPost findJgraphyPostById(Long id) {
    return jgraphyPostRepository.findById(id).orElseThrow(() -> new JgraphyPostNotFoundException(id));
  }
}
