package com.mycomp.jokenpo.service


import com.mycomp.jokenpo.enums.PlayType
import com.mycomp.jokenpo.model.User
import com.mycomp.jokenpo.respository.UserRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class GameService(private val userRepository: UserRepository) {

    private fun returnWinnerBetweenTwoPlayers(u1: User, u2: User): User {

        when (u1.play) {
            PlayType.SPOCK -> when (u2.play) {
                //SPOCK WINS
                PlayType.TESOURA -> return u1
                PlayType.PEDRA -> return u1
                //SPOCK LOSES
                PlayType.PAPEL -> return u2
                PlayType.LAGARTO -> return u2
            }
            PlayType.TESOURA -> when (u2.play) {
                //TESOURA (scissors) WINS
                PlayType.PAPEL -> return u1
                PlayType.LAGARTO -> return u1
                //TESOURA (scissors) LOSES
                PlayType.SPOCK -> return u2
                PlayType.PEDRA -> return u2
            }
            PlayType.PAPEL -> when (u2.play) {
                //PAPEL (paper) WINS
                PlayType.SPOCK -> return u1
                PlayType.PEDRA -> return u1
                //PAPEL (paper) LOSES
                PlayType.TESOURA -> return u2
                PlayType.LAGARTO -> return u2
            }
            PlayType.PEDRA -> when (u2.play) {
                //PEDRA (stone) WINS
                PlayType.LAGARTO -> return u1
                PlayType.TESOURA -> return u1
                //PEDRA (stone) LOSES
                PlayType.SPOCK -> return u2
                PlayType.PAPEL -> return u2
            }
            PlayType.LAGARTO -> when (u2.play) {
                //LAGARTO (lizard) WINS
                PlayType.SPOCK -> return u1
                PlayType.PAPEL -> return u1
                //LAGARTO (lizard) LOSES
                PlayType.TESOURA -> return u2
                PlayType.PEDRA -> return u2
            }
        }
        return u1
    }

    private fun playGame(users: List<User>): User?{
        val winner = users.reduce { a, b ->
            returnWinnerBetweenTwoPlayers(a, b)
        }

        if (users.filter { player -> player.play == winner.play }
                        .count() == 1)
            return winner
        else
            return null
    }

    fun playGameWithAll(): User? {
        val allUsers = userRepository.findAll().toList()

        return playGame(allUsers)

//        val winner = allUsers.reduce { a, b ->
//            returnWinnerBetweenTwoPlayers(a, b)
//        }
//
//        if (allUsers.filter { player -> player.play == winner.play }
//                        .count() == 1)
//            return winner
//        else
//            return null

    }

    fun playGameWithSome(ids: Array<Long?>): User? {

        val someUsers = userRepository.findAllById(ids.asIterable()).toList()

        return playGame(someUsers)

//        val winner = allUsers.reduce { a, b ->
//            returnWinnerBetweenTwoPlayers(a, b)
//        }
//
//        if (allUsers.filter { player -> player.play == winner.play }
//                        .count() == 1)
//            return winner
//        else
//            return null

    }
}