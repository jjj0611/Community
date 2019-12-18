package community.content.jgraphy.api.dto;

import javax.validation.constraints.NotEmpty;

import community.content.jgraphy.domain.JgraphyPost;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JgraphyPostRequestDto {
  private @NotEmpty String title;
  private @NotEmpty String content;

  public static JgraphyPost from(JgraphyPostRequestDto jgraphyPostRequestDto) {
      return JgraphyPost.builder()
              .title(jgraphyPostRequestDto.getTitle())
              .content(jgraphyPostRequestDto.getContent())
              .build();
  }
}
