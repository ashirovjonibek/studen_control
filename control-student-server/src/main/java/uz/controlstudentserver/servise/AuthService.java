package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.entity.User;
import uz.controlstudentserver.jwt.JwtTokenProvider;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.payload.ResToken;
import uz.controlstudentserver.payload.SignIn;
import uz.controlstudentserver.repository.UserRepository;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    DtoService dtoService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username+" not found"));
    }

    public ResToken signIn(SignIn signIn) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User principal = (User) authentication.getPrincipal();
            String jwt = jwtTokenProvider.generateToken(principal);
            return new ResToken(jwt);
        }catch (Exception e){
            return null;
        }
    }

    public User loadByUserId(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(()->new IllegalStateException(uuid+" user not found"));
    }

    public ApiResponse searchUser(String search) {
        return new ApiResponse("Ok",true,userRepository.byUsername(search));
    }

    public ApiResponse all() {
        return new ApiResponse("Ok",true,userRepository.findAll().stream().map(item->dtoService.userDto(item)).collect(Collectors.toList()));
    }
}
