package seminars.fourth.book;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Задание 1. Ответьте письменно на вопросы:
 * 1)  Почему использование тестовых заглушек может быть полезным при написании модульных тестов?
 * Заглушки упрощают написание тестов (повышается скорость работы теста, а иногда без них просто нельзя, например когда
 * тестируемый метод обращается к базе данных)

 2) Какой тип тестовой заглушки следует использовать, если вам нужно проверить, что метод был вызван с определенными аргументами?

 3) Какой тип тестовой заглушки следует использовать, если вам просто нужно вернуть определенное значение или исключение в ответ на вызов метода?

 4) Какой тип тестовой заглушки вы бы использовали для имитации  взаимодействия с внешним API или базой данных?
 *  */

class BookServiceTest {

    /** Idea выдает сообщение
     * "Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended"
     * Не могу понять в чем проблема*/

    @Test
    void shouldFindBookByIdUsingMock() {
        //Создаем Mock для BookService
        BookService mockedBookService = Mockito.mock(BookService.class);

        //Вызываем метод findBookById
        mockedBookService.findBookById("1");

        //Проверяем, что метод findBookById был вызван один раз
        verify(mockedBookService, times(1)).findBookById("1");


        //Получаем книгу из Mock объекта через метод findBookById
        Book book = mockedBookService.findBookById("2");

        //Проверяем что метод Mock объекта вернул null
        assertEquals(book, null);
    }

    @Test
    void shouldFindBookByIdUsingSpy() {
        //Создаем репозиторий книг
        InMemoryBookRepository repository = new InMemoryBookRepository();

        //Создаем объект Spy для BookService
        BookService spyBookService = Mockito.spy(new  BookService(repository));

        //Вызываем метод findBookById
        spyBookService.findBookById("1");

        //Проверяем, что метод findBookById был вызван один раз
        verify(spyBookService, times(1)).findBookById("1");


        //Получаем книгу из Spy объекта через метод findBookById
        Book book = spyBookService.findBookById("2");

        //Находим Id полученной книги
        String id = book.getId();

        //Проверяем результат
        assertEquals(id, "2");
    }

    /** Не понял как создать объект Fake и откуда должен взяться класс BookServiceFake*/
//    @Test
//    void shouldFindBookByIdUsingFake() {
//        //Создаем репозиторий книг
//        InMemoryBookRepository repository = new InMemoryBookRepository();
//
//        //Создаем объект Fake для BookService
//        BookService fakeBookService = new BookServiceFake();
//
//    }

    @Test
    void shouldFindAllBooksUsingMock() {
        //Создаем Mock для BookService
        BookService mockedBookService = Mockito.mock(BookService.class);

        //Вызываем метод findAllBooks
        mockedBookService.findAllBooks();

        //Проверяем, что метод findAllBooks был вызван один раз
        verify(mockedBookService, times(1)).findAllBooks();


        //Получаем книги из Mock объекта через метод findAllBooks
        List<Book> books = mockedBookService.findAllBooks();

        //Проверяем что список книг пришел пустой
        assertEquals(books.size(), 0);
    }

    @Test
    void shouldFindAllBooksUsingSpy() {
        //Создаем репозиторий книг
        InMemoryBookRepository repository = new InMemoryBookRepository();

        //Создаем объект Spy для BookService
        BookService spyBookService = Mockito.spy(new  BookService(repository));

        //Вызываем метод findAllBooks
        spyBookService.findAllBooks();

        //Проверяем, что метод findAllBooks был вызван один раз
        verify(spyBookService, times(1)).findAllBooks();


        //Получаем книги из Spy объекта через метод findAllBooks
        List<Book> books = spyBookService.findAllBooks();

        //Проверяем что список книг пришел пустой
        assertEquals(books.size(), 2);
    }
}