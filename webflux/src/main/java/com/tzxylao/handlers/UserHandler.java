package com.tzxylao.handlers;

import com.tzxylao.domain.User;
import com.tzxylao.repository.UserRepository;
import com.tzxylao.util.CheckUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

/**
 * @author laoliangliang
 * @since 19/4/20 下午8:06
 */
@Component
public class UserHandler {

    private final UserRepository repository;

    public UserHandler(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * 得到所有用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(this.repository.findAll(), User.class);
    }

    /**
     * 创建用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class);
        return user.flatMap(u ->{
            //校验代码
            CheckUtil.checkName(u.getName());
            return ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(this.repository.saveAll(user), User.class);
        });
    }

    /**
     * 根据id删除用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> deleteUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.repository.findById(id).flatMap(user -> this.repository.delete(user)
                .then(ok().build()))
                .switchIfEmpty(notFound().build());
    }
}
