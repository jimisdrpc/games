package com.mycomp.jokenpo

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mycomp.jokenpo.enums.PlayType
import com.mycomp.jokenpo.model.User
import com.mycomp.jokenpo.respository.UserRepository
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.web.util.NestedServletException

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class, MockitoExtension::class)
class JokenpoApplicationTests {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@MockBean
	lateinit var respository: UserRepository

	@BeforeEach
	fun setup() {
		whenever(respository.save(User(1, "Test", PlayType.LAGARTO))).thenAnswer {
			it.arguments.first()
		}
	}

	@Test
	fun `Test createUser in the happy path scenario`() {
		val user = User(1, "Test", PlayType.LAGARTO)
		mockMvc.post("/users/") {
			contentType = MediaType.APPLICATION_JSON
			content = jacksonObjectMapper().writeValueAsString(user)
			accept = MediaType.APPLICATION_JSON
		}.andExpect {
			status { isOk }
			content { contentType(MediaType.APPLICATION_JSON) }
			content { json("""{"id":1,"name":"Test"}""") }
		}
		//verify(respository, times(1)).save(user)
	}

	@Test
	fun `Test negative scenario of createUser`() {
		val user = User(2, "Test", PlayType.LAGARTO)
		assertThrows<NestedServletException> {
			mockMvc.post("/users/") {
				contentType = MediaType.APPLICATION_JSON
				content = jacksonObjectMapper().writeValueAsString(user)
				accept = MediaType.APPLICATION_JSON
			}
		}
		verify(respository, times(1)).save(user)
	}

	@Test
	fun findUser() {
		mockMvc.get("/users?id=99")
				.andExpect {
					status { isOk }
				}
		verify(respository, times(1)).findAll()
	}
}