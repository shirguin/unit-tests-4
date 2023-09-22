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
 *
 * Заглушка выступает в качестве небольшого фрагмента кода, заменяющего другой компонент во время тестирования.
 * Одним из ключевых преимуществ использования заглушки является их способность последовательно возвращать результаты,
 * что упрощает написание тестов. Даже если другие компоненты еще не полностью функциональны, вы по-прежнему можете
 * выполнять тесты с помощью заглушки.
 *
 * 2) Какой тип тестовой заглушки следует использовать, если вам нужно проверить, что метод был вызван с определенными
 * аргументами?
 *
 * Моки — это классы-заглушки, которые используются чтобы проверить, что определенная функция была вызвана с определенными
 * аргументами.
 *
 * 3) Какой тип тестовой заглушки следует использовать, если вам просто нужно вернуть определенное значение или исключение
 * в ответ на вызов метода?
 *
 * Стабы — это классы-заглушки, которые вместо выполнения действия возвращают какие-то данные. Например, стаб класса
 * работы с базой данных может вместо реального обращения к базе данных возвращать, что запрос успешно выполнен.
 * А при попытке прочитать что-то из нее возвращает готовый массив с данными.
 *
 * 4) Какой тип тестовой заглушки вы бы использовали для имитации  взаимодействия с внешним API или базой данных?
 *
 * Я бы использовал Stub заглушку. С ее помощью можно написать готовый ответ, например базы данных и это сильно упростило бы
 * написание теста.
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

    /** Не понял как создать объект Fake и откуда должен взяться класс BookServiceFake
     * Почему то для Fake другой синтаксис, отличный от Mock и Spy*/
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