package com.streamit.streamitdemo.service.impl;
import com.streamit.streamitdemo.model.entity.Show;
import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.model.responsemodels.ShowResponseModel;
import com.streamit.streamitdemo.model.service.ShowServiceModel;
import com.streamit.streamitdemo.model.service.UserServiceModel;
import com.streamit.streamitdemo.model.view.ShowViewModel;
import com.streamit.streamitdemo.repository.ShowRepository;
import com.streamit.streamitdemo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowServiceImplTest {
    @Mock
    private ShowRepository showRepository;
    @Mock
    private UserService userService;
    @Mock
    private ModelMapper modelMapper;
    private ShowServiceImpl showService;
    private UserServiceModel testUser;
    private List<Show> allShows;



    @Before
    public void setUp() {
        showService = new ShowServiceImpl(showRepository, userService, modelMapper);
        Set<ShowServiceModel> testShows = new HashSet<>();
        ShowServiceModel testShowServiceModel = new ShowServiceModel();
        testShowServiceModel.setId(1L);
        testShowServiceModel.setShowName("testShowServiceModel");
        testShows.add(testShowServiceModel);
        testUser = new UserServiceModel() {{
            setUsername("username");
            setShows(testShows);
        }};
        allShows=List.of(
                new Show(){{
                    setId(1L);
                    setShowName("test show name");
                    setDate(LocalDateTime.of(2020,1,1,20,1,1));
                    setUsers(Set.of(
                            new User(){{
                                setId(1L);
                                setUsername("testUser");
                            }}));
                }},
                new Show(){{
                        setId(2L);
                        setShowName("test show name1");
                        setDate(LocalDateTime.of(2021, 1, 1, 20, 1, 1));
                    }}
        );
        when(showRepository.findAll()).thenReturn(allShows);
    }

    @Test
    public void testFindAllShowsByUser() {
        //arrange
        when(userService.findByUsername("username")).thenReturn(testUser);
        //act
        List<ShowViewModel> shows = showService.findAllShowsByUser("username");
        //assert
        assertThat(shows.size(), is(1));

    }

    @Test
    public void testFindAllShows(){
        //act
        List<ShowResponseModel> showResponseModels = showService.findAllShows();
        //assert
        assertThat(showResponseModels.size(),is(2));
    }

    @Test
    public void testDeleteAllPastShows(){
        //act
        showService.deleteAllPastShows();
        //assert
        verify(showRepository,times(1)).deleteInBatch(showRepository.findAll());
    }

    @Test
    public void testDeleteById() {
     //arrange
        when(showRepository.findById(1L)).thenReturn(java.util.Optional.of(new Show() {{
            setId(1L);
            setShowName("test show name");
            setDate(LocalDateTime.of(2020, 1, 1, 20, 1, 1));
            setUsers(Set.of(
                    new User(){{
                        setId(1L);
                        setUsername("testUser");
                    }}));
        }}));
        Long showId=1L;
        // perform the call
        showService.delete(showId,"testUser");
        // verify the mocks
        verify(showRepository, times(1)).deleteById(eq(showId));

    }
}
