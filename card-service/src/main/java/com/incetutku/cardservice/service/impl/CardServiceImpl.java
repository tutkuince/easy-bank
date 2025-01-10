package com.incetutku.cardservice.service.impl;

import com.incetutku.cardservice.constants.CardConstants;
import com.incetutku.cardservice.dto.CardDto;
import com.incetutku.cardservice.entity.Card;
import com.incetutku.cardservice.exception.CardAlreadyExistsException;
import com.incetutku.cardservice.exception.ResourceNotFoundException;
import com.incetutku.cardservice.mapper.CardMapper;
import com.incetutku.cardservice.repository.CardRepository;
import com.incetutku.cardservice.service.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements ICardService {

    private final CardRepository cardRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
        if (optionalCard.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber " + mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    @Override
    public CardDto fetchLoanByMobileNumber(String mobileNumber) {
        Card selectedCard = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Mobile Number", mobileNumber)
        );
        return CardMapper.mapToCardDto(selectedCard, new CardDto());
    }

    @Override
    public boolean updateLoan(CardDto cardDto) {
        Card selectedCard = cardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Mobile Number", cardDto.getMobileNumber())
        );
        CardMapper.mapToCard(cardDto, selectedCard);
        cardRepository.save(selectedCard);
        return true;
    }

    @Override
    public boolean deleteLoanByMobileNumber(String mobileNumber) {
        Card selectedCard = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Mobile Number", mobileNumber)
        );
        cardRepository.deleteById(selectedCard.getCardId());
        return true;
    }

    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}
